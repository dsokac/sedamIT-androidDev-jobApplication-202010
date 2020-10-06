package danijelsokac.android.personaldatamanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import danijelsokac.android.personaldatamanagement.R;
import danijelsokac.android.personaldatamanagement.adapters.view_holders.PersonViewHolder;
import danijelsokac.android.personaldatamanagement.listeners.DialogFragmentListener;
import danijelsokac.android.personaldatamanagement.models.UserModel;

public class PeopleAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private List<UserModel> data;
    private Context context;
    private FragmentManager fragmentManager;
    private  DialogFragmentListener listener;

    public PeopleAdapter(List<UserModel> data, Context context) {
        this.context = context;
        this.data = data;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setListener(DialogFragmentListener listener) {
        this.listener = listener;
    }

    public void addItem(UserModel user) {
        this.data.add(user);
        notifyItemInserted(this.data.size() - 1);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_layout, parent, false);
        PersonViewHolder vh = new PersonViewHolder(view);
        vh.setFragmentManager(this.fragmentManager);
        vh.setListener(listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.bind(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    public void updateItem(UserModel user) {
        int location = -1;
        for(int i = 0; i < data.size(); i++) {
            UserModel item = data.get(i);
            if(user.getId() == item.getId()) {
                location = i;
                break;
            }
        }
        if(location != -1) {
            data.remove(location);
            data.add(location, user);
            notifyItemChanged(location);
        }
    }
}
