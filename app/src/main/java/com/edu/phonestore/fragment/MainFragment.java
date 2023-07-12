package com.edu.phonestore.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edu.phonestore.R;
import com.edu.phonestore.adapter.ProductAdapter;
import com.edu.phonestore.click.IClickProduct;
import com.edu.phonestore.databinding.FragmentMainBinding;
import com.edu.phonestore.model.Product;
import com.edu.phonestore.presenter.ProductPresenter;
import com.edu.phonestore.view.ProductView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements ProductView, IClickProduct {

    private FragmentMainBinding mBinding;
    private View mView;
    private ProductPresenter productPresenter;
    private ProductAdapter productAdapter;
    private List<Product> products;
//    private RecyclerView recyclerProduct;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "reload fragment", Toast.LENGTH_SHORT).show();
        getProducts();
//        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_main, container, false);

        mBinding = FragmentMainBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();

        initialView();
        initialData();

        return mView;
    }

    private void initialView() {
        System.out.println("mmmm 1111111111111111111111111111111111111111");
        Toast.makeText(getContext(), "main fragment", Toast.LENGTH_SHORT).show();
        productPresenter = new ProductPresenter(getActivity().getApplication(), getActivity(), this);
        mBinding.recyclerProduct.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.recyclerProduct.setLayoutManager(layoutManager);


        getProducts();
    }

    private void initialData() {
    }

    private void getProducts() {
        productPresenter.getProducts();
    }
    private void updateUI() {
        System.out.println("mmmm 2222222222222222222222222222222222222222222");
        if (productAdapter == null){
//            productAdapter = new ProductAdapter(products, getContext().getApplicationContext());
            productAdapter = new ProductAdapter(products, getContext().getApplicationContext(), this);
            mBinding.recyclerProduct.setAdapter(productAdapter);
        } else {
            productAdapter.notifyDataSetChanged();
        }
    }

    public void reloadData() {
        Toast.makeText(getActivity(), "reload fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void productSuccess(List<Product> productList) {
        System.out.println("mmmm 33333333333333333333333333333333333333333333");
        Toast.makeText(getContext(), "get products success", Toast.LENGTH_SHORT).show();
        System.out.println("products: " + productList);
        products = productList;
        updateUI();
    }

    @Override
    public void productFailed(String msg) {

    }

    @Override
    public void clickImage(Product product) {
        Toast.makeText(getContext(), "click image product", Toast.LENGTH_SHORT).show();
    }
}