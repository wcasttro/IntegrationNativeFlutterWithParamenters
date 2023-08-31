package com.example.integrartion

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.integrartion.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel


class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var flutterEngine : FlutterEngine



    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

       flutterEngine =  FlutterEngine(this);


        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        val calculatorChannel =  MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "samples.flutter.dev/background")

        calculatorChannel.invokeMethod("executeBackgroundCode", null)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "samples.flutter.dev/background")
            .invokeMethod("executeBackgroundCode", null)

        calculatorChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "executeBackgroundCode" -> {
                    var value = call.arguments as String
                    print(value);                    //result.success(value)
                }
                else -> result.notImplemented()
            }
        }

        //        // Instantiate a FlutterEngine.
//        flutterEngine =  FlutterEngine(this);
//
//        // Start executing Dart code to pre-warm the FlutterEngine.
//        flutterEngine.getDartExecutor().executeDartEntrypoint(
//            DartExecutor.DartEntrypoint.createDefault()
//        );
//
//        // Cache the FlutterEngine to be used by FlutterActivity.
//        FlutterEngineCache
//            .getInstance()
//            .put("my_engine_id", flutterEngine);

//        val intent = FlutterActivity
//            .withNewEngine()
//            .initialRoute("Android 中启动 FlutterActivity")
//            .build(this@MainActivity)
//        intent.putExtra("initParams", "Android 中启动 FlutterActivity2")

        binding.fab.setOnClickListener { view ->
            // startActivity(intent)

            startActivity(
                FlutterActivity
                    .withNewEngine()
                    .initialRoute("/ScreenTeste")
                    .build(this)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}