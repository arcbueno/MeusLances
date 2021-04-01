import 'package:flutter/material.dart';
import 'package:meus_lanches/model/product.dart';
import 'package:meus_lanches/pages/home_page/home_viewmodel.dart';
import 'package:meus_lanches/service/product_service.dart';
import 'package:meus_lanches/service_locator.dart';
import 'package:meus_lanches/widgets/home_list_item.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var _viewModel = HomeViewModel(getIt.get<ProductService>());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Meus Lanches"),
      ),
      body: Center(
        child: StreamBuilder<List<Product>>(
          initialData: [],
          stream: _viewModel.listaStream,
          builder: (context, snap) {
            return ListView.builder(
              itemCount: snap.data.length,
              itemBuilder: (context, index) {
                var item = snap.data[index];
                return HomeListItem(item: item);
              },
            );
          },
        ),
      ),
      // floatingActionButton: FloatingActionButton(
      //   onPressed: _incrementCounter,
      //   tooltip: 'Increment',
      //   child: Icon(Icons.add),
      // ),
    );
  }
}
