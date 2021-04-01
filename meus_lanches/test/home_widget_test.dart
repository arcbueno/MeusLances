import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:meus_lanches/pages/home_page/home_page.dart';
import 'package:meus_lanches/service_locator.dart';
import 'package:meus_lanches/widgets/home_list_item.dart';

main() {
  setupLocator();
  testWidgets(
    'Should verify if list contains one or more items',
    (WidgetTester tester) async {
      await tester.pumpWidget(
        MaterialApp(
          home: MyHomePage(),
        ),
      );
      final listItem = find.byType(HomeListItem);
      expect(listItem, findsWidgets);
    },
  );
}
