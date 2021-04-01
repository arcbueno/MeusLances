import 'package:flutter/material.dart';
import 'package:meus_lanches/model/product.dart';

class HomeListItem extends StatelessWidget {
  final Product item;

  const HomeListItem({Key key, this.item}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Icon(Icons.fastfood),
      title: Text(item.name),
    );
  }
}
