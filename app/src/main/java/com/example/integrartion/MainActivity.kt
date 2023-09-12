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
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel


class MainActivity : AppCompatActivity() {

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

// Instantiate a FlutterEngine.
        flutterEngine =  FlutterEngine(this);
        // Configure an initial route.
        val channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "channel_name")
        channel.invokeMethod("sendParamsToFlutter", "valor a ser passado");

        //flutterEngine.getNavigationChannel().setInitialRoute("/homePage");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        );
        // Cache the FlutterEngine to be used by FlutterActivity or FlutterFragment.
        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine);


        // Define a rota nomeada do Flutter para ser aberta

        // Define a rota nomeada do Flutter para ser aberta
//        val intent = FlutterActivity
//            .withNewEngine()
//            .initialRoute("/homePage?teste1=teste")
//            .build(this)
//        intent.putExtra("initParams", "teste")

        binding.fab.setOnClickListener { view ->


            startActivity(
                FlutterActivity
                    .withCachedEngine("my_engine_id")
                    .build(this)
            );

         //   startActivity(intent)

//            startActivity(
//                FlutterActivity
//                    .withNewEngine()
//                    .initialRoute("ScreenTeste?parm1=um&parm2=dois")
//                    .build(this)
//            )
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