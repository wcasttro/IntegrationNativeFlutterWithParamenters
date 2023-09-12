import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  @override
  void didChangeDependencies() {
    var args = ModalRoute.of(context)?.settings.arguments;
    if (args != null) {
      args = args as Map;
    }
    print(args);
    super.didChangeDependencies();
  }

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    var args = ModalRoute.of(context)?.settings.arguments;
    if (args != null) {
      args = args as Map;
    }

    print(args);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
            TextField(),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
