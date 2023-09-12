import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  const platform = const MethodChannel('samples.flutter.dev/background');

  platform.setMethodCallHandler((call) {
    if (call.method == 'executeBackgroundCode') {
      log('testando ....');
    }

    return Future.value('testando ....');
  });

  platform.invokeMethod('executeBackgroundCode', 'teste');

  // runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //  platform.invokeMapMethod('bora lá');

    return MaterialApp(/* ... */);
  }
}
