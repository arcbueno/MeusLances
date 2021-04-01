import 'package:get_it/get_it.dart';
import 'package:meus_lanches/api/product_api.dart';
import 'package:meus_lanches/service/product_service.dart';

final getIt = GetIt.instance;

void setupLocator() {
  getIt.registerSingleton<ProductApi>(
    ProductApi(),
  );

  getIt.registerSingleton<ProductService>(
    ProductService(
      getIt.get<ProductApi>(),
    ),
  );
}
