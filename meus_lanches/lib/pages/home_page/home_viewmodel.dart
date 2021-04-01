import 'package:meus_lanches/model/product.dart';
import 'package:meus_lanches/service/product_service.dart';
import 'package:rxdart/rxdart.dart';

class HomeViewModel {
  final ProductService service;

  final _lista = BehaviorSubject<List<Product>>();
  Stream<List<Product>> get listaStream => _lista.stream;

  HomeViewModel(this.service) {
    service.getAll().then((value) {
      _lista.add(value);
    });
  }

  void dispose() {
    _lista.close();
  }
}
