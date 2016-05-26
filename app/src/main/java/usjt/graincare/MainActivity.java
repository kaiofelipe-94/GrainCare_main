package usjt.graincare;

/**
 * Created by kaio on 21/05/2016.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String opcao;
    ArrayList<Silo> silos;
    final String servidor = "192.168.0.13:8080/servico_REST02";  //endereço IPV4
    //final String servidor = "10.0.3.2:8080/servico_REST02";


    SiloRequester requester;
    ProgressBar mProgress;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //------------------------ Código gerado ------------------------------------------------------------------------------------------------------


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_new_silo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add silo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //------------------------ Fim Código gerado ------------------------------------------------------------------------------------------------------


        setupViews();
        consultarSilos();
    }

    private void setupViews()
    {
        mProgress = (ProgressBar) findViewById(R.id.carregando);
        mProgress.setVisibility(View.VISIBLE);
    }



    // constante static para identificar o parametro
    public final static String SILO = "br.usjt.SILOS";

    //será chamado quando o usuário clicar em enviar
    public void consultarSilos()
    {
        //final String pOpcao = this.opcao.equals("Escolha uma opção") ? "" : opcao;
        final String pOpcao = "silos";

        requester = new SiloRequester();
        if (requester.isConnected(this))
        {
            intent = new Intent(this, ListaSiloActivity.class);

            //mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        silos = requester.get("http://" + servidor + "/selecao.json", pOpcao);
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                intent.putExtra(SILO, silos);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else
        {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //if (id == R.id.nav_camera) {
        // Handle the camera action
        // } else if (id == R.id.nav_gallery) {

        //} else if (id == R.id.nav_slideshow) {

        //} else if (id == R.id.nav_manage) {

        //} else if (id == R.id.nav_share) {

        //} else if (id == R.id.nav_send) {

        //}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
