import 'package:flutter/material.dart';

class ScreenTeste extends StatefulWidget {
  const ScreenTeste({super.key});

  @override
  State<ScreenTeste> createState() => _ScreenTesteState();
}

class _ScreenTesteState extends State<ScreenTeste> {
  @override
  void didChangeDependencies() {
    var args = ModalRoute.of(context)?.settings.arguments;
    if (args != null) {
      args = args as Map;
    }
    print(args);
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    return Container();
  }
}
