package com.edu.phonestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.phonestore.R;
import com.edu.phonestore.click.IClickProduct;
import com.edu.phonestore.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;
    private IClickProduct iClickProduct;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public ProductAdapter(List<Product> products, Context context, IClickProduct iClickProduct) {
        this.products = products;
        this.context = context;
        this.iClickProduct = iClickProduct;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row_product, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        if (product == null) {
            return;
        }

        holder.txtName.setText(products.get(position).getName());
        holder.txtDescription.setText(products.get(position).getDescription());
        if (products.get(position).getImages().size() > 0) {
            String urlImage = "";
            if (position % 4 == 0) {
                urlImage = "https://i.9mobi.vn/cf/Images/np/2022/8/30/hinh-nen-14.jpg";
            } else if (position % 4 == 1) {
                urlImage = "https://i.9mobi.vn/cf/Images/np/2022/8/30/hinh-nen-9.jpg";
            } else if (position % 4 == 2) {
                urlImage = "https://i.9mobi.vn/cf/Images/np/2022/8/30/hinh-nen-4.jpg";
            } else if (position % 4 == 3) {
                urlImage = "https://i.9mobi.vn/cf/Images/np/2022/8/30/hinh-nen-26.jpg";
            }
            int maxPos = products.get(position).getImages().size() - 1;
            if (products.get(position).getImages().get(maxPos).getUrl() != null) {
//                urlImage = products.get(position).getImages().get(maxPos).getUrl();
                System.out.println("url123: " + urlImage);
                Picasso.with(context).load(urlImage)
                        .placeholder(R.drawable.img_a)
                        .error(R.drawable.img_b)
//                        .error(R.drawable.img_default)
                        .into(holder.imgProduct);
            } else {
                holder.imgProduct.setImageResource(R.mipmap.logo_shop);
            }
        } else {
            holder.imgProduct.setImageResource(R.mipmap.logo_shop);
        }

        /** event click */
        holder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickProduct.clickImage(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDescription;
        ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtDescription = (TextView) itemView.findViewById(R.id.txt_description);
            imgProduct = (ImageView) itemView.findViewById(R.id.img_product);
        }
    }
}
