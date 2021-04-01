import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:meus_lanches/pages/home_page/home_page.dart';
import 'package:meus_lanches/service_locator.dart';

var options = BaseOptions(
  baseUrl: 'https://meus-lanches-api.herokuapp.com/api/v1',
);
final dio = Dio(options);
void main() {
  setupLocator();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Meus Lanches',
      theme: ThemeData(
        primarySwatch: Colors.orange,
      ),
      home: MyHomePage(),
    );
  }
}
