import 'package:meus_lanches/main.dart';
import 'package:meus_lanches/model/product.dart';

class ProductApi {
  final route = '/products';

  Future<List<Product>> getAll() async {
    var result = await dio.get<List<dynamic>>(route);
    var listaFinal = result.data.map((e) => Product.fromMap(e)).toList();
    return listaFinal;
  }

  Future<bool> insert(Product produto) async {
    var json = produto.toInsert();
    var result = await dio.post(route, data: json);
    return result.statusCode == 202;
  }
}
