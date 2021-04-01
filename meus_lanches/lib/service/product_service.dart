import 'package:meus_lanches/api/product_api.dart';
import 'package:meus_lanches/model/product.dart';

class ProductService {
  final ProductApi api;

  ProductService(this.api);

  Future<List<Product>> getAll() async {
    return await api.getAll();
  }

  Future<bool> newProduct(Product produto) async {
    return await api.insert(produto);
  }
}
