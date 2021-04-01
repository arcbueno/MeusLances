import 'dart:convert';

class Product {
  int id;
  String name;
  String description;
  String category;
  double price;

  Product({
    this.id,
    this.name,
    this.description,
    this.category,
    this.price,
  }) : assert(name != null);

  Product copyWith({
    int id,
    String name,
    String description,
    String category,
    double price,
  }) {
    return Product(
      id: id ?? this.id,
      name: name ?? this.name,
      description: description ?? this.description,
      category: category ?? this.category,
      price: price ?? this.price,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'description': description,
      'category': category,
      'price': price,
    };
  }

  Map<String, dynamic> toInsert() {
    return {
      'name': name,
      'description': description,
      'category': category,
      'price': price,
    };
  }

  factory Product.fromMap(Map<String, dynamic> map) {
    return Product(
      id: map['id'],
      name: map['name'],
      description: map['description'],
      category: map['category'],
      price: map['price'],
    );
  }

  String toJson() => json.encode(toMap());

  factory Product.fromJson(String source) =>
      Product.fromMap(json.decode(source));
}
