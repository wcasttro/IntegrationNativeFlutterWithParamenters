import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:module_flutter/homePage.dart';
import 'package:module_flutter/screen_test.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: chooseWidget(window.defaultRouteName, context),
    );
  }

  Widget chooseWidget(String route, context) {
    final route_sem_parametro = route.split('?').first;
    // final args = ModalRoute.of(context)?.settings.arguments as Map;

    switch (route_sem_parametro) {
      // name of the route defined in the host app
      case 'homePage':
        return MyHomePage(
          title: '',
        );

      case 'ScreenTeste':
        return ScreenTeste();

      default:
        return Center(
          child: Text('Unknown'),
        );
    }
  }
}
