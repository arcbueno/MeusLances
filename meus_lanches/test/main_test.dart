import 'package:flutter_test/flutter_test.dart';
import 'package:meus_lanches/api/product_api.dart';
import 'package:meus_lanches/model/product.dart';
import 'package:meus_lanches/service/product_service.dart';

void main() {
  var service = ProductService(
    ProductApi(),
  );

  test(
    'Should create a new product instance',
    () {
      var produto = Product(
        id: null,
        name: "Criado para testar",
        description: "unit test com saulo",
        category: 'Teste',
        price: 1000,
      );
      expect(produto.name, isNotNull);
      expect(produto.name, isNotEmpty);
    },
  );

  test(
    'Should create a new product instance and throw assert error',
    () {
      expect(
        () => Product(),
        throwsAssertionError,
      );
    },
  );

  test(
    'Should return true after creating new product',
    () async {
      var customProduct = Product(
        id: null,
        name: "Criado para testar",
        description: "unit test com saulo",
        category: 'Teste',
        price: 1000,
      );
      var result = await service.newProduct(customProduct);
      expect(
        result,
        true,
      );
    },
  );

  test(
    'Should return a list after requesting',
    () async {
      var result = await service.getAll();
      expect(
        result,
        isNotNull,
      );
    },
  );
}
