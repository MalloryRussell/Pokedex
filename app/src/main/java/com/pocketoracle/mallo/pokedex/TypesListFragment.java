package com.pocketoracle.mallo.pokedex;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypesListFragment extends Fragment {
    private TypesListFragmentListener typesListFragmentListener;
    public interface TypesListFragmentListener
    {
        public void onTypeClicked(String type);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            typesListFragmentListener = (TypesListFragmentListener) activity;
        }
        catch(ClassCastException e)
        {
            new ClassCastException(activity.getClass().getSimpleName() + " MUST IMPLEMENT typesListFragmentListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_types_list, container, false);

        DBAccessHelper dbAccess = DBAccessHelper.getInstance(this.getActivity());
        dbAccess.open();
        List<String> types = dbAccess.getTypes();

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(inflater.getContext(),
                        android.R.layout.simple_list_item_1,
                        types);
        ListView lvTypes = (ListView) view.findViewById(R.id.lvTypes);
        lvTypes.setAdapter(arrayAdapter);
        lvTypes.setOnItemClickListener(new TypeClickedHandler());

        // Inflate the layout for this fragment
        return view;
    }
    private class TypeClickedHandler implements OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            String type = parent.getItemAtPosition(position).toString();
            typesListFragmentListener.onTypeClicked(type);
        }
    }
}