package edu.cnm.deepdive.criminalintent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
/*
A controller class.
 */
public class CrimeListFragment extends Fragment {
  private RecyclerView crimeRecyclerView;
  private CrimeAdapter mAdapter;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater
        .inflate(R.layout.fragment_crime_list, container, false);
    crimeRecyclerView = (RecyclerView) view
        .findViewById(R.id.crime_recycler_view);
    crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    updateUI();
    return view;
  }
  private void updateUI() {
    CrimeLab crimeLab = CrimeLab.get(getActivity());
    List<Crime> crimes = crimeLab.getCrimes();
    mAdapter = new CrimeAdapter(crimes);
    crimeRecyclerView.setAdapter(mAdapter);
  }
  private class CrimeHolder extends RecyclerView.ViewHolder implements
      View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private Crime mCrime;
    public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
      super(inflater.inflate(R.layout.list_item_crime, parent, false));
      itemView.setOnClickListener(this);
      mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
      mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
    }
    public void bind(Crime crime) {
      mCrime = crime;
      mTitleTextView.setText(mCrime.getTitle());
      mDateTextView.setText(mCrime.getDate().toString());
    }
    @Override
    public void onClick(View view) {
      Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!",
          Toast.LENGTH_SHORT).show();
    }
  }
  private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
    private List<Crime> mCrimes;
    public CrimeAdapter(List<Crime> crimes) {
      mCrimes = crimes;
    }
    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent,
        int viewType) {
      LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
      return new CrimeHolder(layoutInflater, parent);
    }
    @Override
    public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
      Crime crime = mCrimes.get(position);
      holder.bind(crime);
    }
    @Override
    public int getItemCount() {
      return mCrimes.size();
    }
  }
}