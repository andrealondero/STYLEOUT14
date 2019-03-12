package com.example.styleout14.PrimoAccesso;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.styleout14.DB.DBAdapterLogin;
import com.example.styleout14.Home.Vestito;
import com.example.styleout14.PrimoAccesso.Top.Top;
import com.example.styleout14.PrimoAccesso.Top.TopRecyclerAdapter;
import com.example.styleout14.R;

import java.util.ArrayList;
import java.util.List;

public class TopFragment extends Fragment {

    View view;
    private RecyclerView myRecyclerView;
    private List<Top> lstTop;
    private ImageView topImageView;
    private Button button;
    private Vestito vestito;
    private DBAdapterLogin db;

    public static final String[] titles = new String[] {
            "Apricot", "AshGray", "Azure", "Beige", "Black", "Blue", "BlueGray", "BlueJeans",
            "BottleGreen", "Celeste", "Coral", "DarkGreen", "Gold", "Gray", "Green",
            "GreenBlue", "Lavender", "LightBlue", "Magenta", "MidnightBlue", "MintGreen",
            "NavyBlue", "OceanBlue", "OceanGreen", "Olive", "Orange", "Pink", "Red", "Rose",
            "Sand", "Scarlet", "Silver", "Tangerine", "Turquoise", "Violet", "White", "Yellow"};

    public static final String[]hexcodes = {
            "#FACEB1", "#B4BFB7", "#0080FF", "#F5F5DC", "#000000", "#0000FF", "#6699CC", "#5CAEED", "#006B4F",
            "#B2FFFF", "#FF7E4F", "#013321", "#A67F00", "#808080", "#008000", "#1065B5", "#E6E6FA", "#ACD7E5", "#D1417F", "#191970", "#3EB589",
            "#1975D1", "#4F41B5", "#49BF92", "#808000", "#FF6600", "#FFBFCA", "#C40233", "#FF0080", "#C2B180", "#FF2200",
            "#BFBFBF", "#F28500", "#41E0D0", "#8702B0", "#FFFFFF", "#FFD400"};

    public static final Integer[] images = {
            R.drawable.apricot, R.drawable.ashgray, R.drawable.azure, R.drawable.beige,
            R.drawable.black, R.drawable.blue, R.drawable.bluegray, R.drawable.bluejeans,
            R.drawable.bottlegreen, R.drawable.celeste, R.drawable.coral, R.drawable.darkgreen,
            R.drawable.gold, R.drawable.gray, R.drawable.green, R.drawable.greenblue,
            R.drawable.lavender, R.drawable.lightblue, R.drawable.magenta, R.drawable.midnightblue,
            R.drawable.mintgreen, R.drawable.navyblue, R.drawable.oceanblue, R.drawable.oceangreen,
            R.drawable.olive, R.drawable.orange, R.drawable.pink, R.drawable.red,
            R.drawable.rose, R.drawable.sand, R.drawable.scarlet, R.drawable.silver,
            R.drawable.tangerine, R.drawable.turquoise, R.drawable.violet, R.drawable.white,
            R.drawable.yellow};

    Spinner spinner;
    List<RowItem> rowItems;

    Spinner fabricSelect;

    public TopFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.top_fragment, container, false);

        button = view.findViewById(R.id.topButtonAdd);
        db = new DBAdapterLogin(view.getContext());

        topImageView = view.findViewById(R.id.imagechange_top);

        vestito = new Vestito();
        vestito.setNome("Vestito");
        vestito.setDisponibile("1");

        myRecyclerView = (RecyclerView) view.findViewById(R.id.top_recyclerview);
        TopRecyclerAdapter recyclerViewAdapter = new TopRecyclerAdapter(getContext(), lstTop);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        myRecyclerView.addOnItemTouchListener( new RecyclerItemClickListener( getContext(),
                myRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( view.getContext(), ""+position, Toast.LENGTH_SHORT).show();
                vestito.setTipoVestito(Integer.toString(position+1));
                vestito.setPic_tag(lstTop.get(position).getModelImage());
                topImageView.setImageResource(lstTop.get(position).getModelImage());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        } ) );

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem( images[i], titles[i] );
            rowItems.add( item );
        }

        spinner = (Spinner) view.findViewById(R.id.topspinner);
        ColourSelectorAdapter adapter = new ColourSelectorAdapter( getActivity(),
                R.layout.colour_spinner, R.id.color, rowItems);
        spinner.setAdapter( adapter );
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemvalue = parent.getItemAtPosition( position ).toString();
                Toast.makeText( getActivity(), "SELECTED" + itemvalue, Toast.LENGTH_SHORT ).show();
                vestito.setColore(itemvalue);
                vestito.setColorCode(hexcodes[position]);
                topImageView.setBackgroundColor( Color.parseColor(hexcodes[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        fabricSelect = (Spinner) view.findViewById(R.id.fabrictopselect);
        List<String> list = new ArrayList<>();
        // lightweight fabrics
        list.add("Cotton"); list.add("Silk");
        // mesh fabrics
        list.add("Cape"); list.add("Lace"); list.add("Tarlatan");
        // medium weight fabrics
        list.add("Cashmere"); list.add("Crepe");list.add("Flannel"); list.add("Poplin"); list.add("RawSilk"); list.add("Sateen");
        // piled fabrics
        list.add("BrushDenim");list.add("Fur"); list.add("Microfiber"); list.add("Suede"); list.add("Velvet");
        // heavy weight fabrics
        list.add("Canvas"); list.add("Denim"); list.add("Tartan"); list.add("Upholstery");
        // Shiny glossy fabrics
        list.add("Satin"); list.add("Silk"); list.add("PolishedCotton");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fabricSelect.setAdapter(adapter1);
        fabricSelect.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addVestito(vestito.getColore(), vestito.getColorCode(), Integer.parseInt(vestito.isDisponibile()),
                        vestito.getNome(), "avorio", Integer.parseInt(vestito.getTipoVestito()), vestito.getPic_tag());
                Toast.makeText(view.getContext(), "vestito aggiunto", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        lstTop = new ArrayList<>();
        lstTop.add(new Top(R.drawable.cardigan));
        lstTop.add(new Top(R.drawable.cardigan_uno));
        lstTop.add(new Top(R.drawable.hoodie));
        lstTop.add(new Top(R.drawable.hoodie_uno));
        lstTop.add(new Top(R.drawable.hoodie_two));
        lstTop.add(new Top(R.drawable.necked));
        lstTop.add(new Top(R.drawable.pullover));
        lstTop.add(new Top(R.drawable.lons_sleevs));
    }
}
