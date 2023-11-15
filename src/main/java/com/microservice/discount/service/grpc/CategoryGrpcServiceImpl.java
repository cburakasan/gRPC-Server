package com.microservice.discount.service.grpc;

import com.microservice.discount.model.AllCategoryResp;
import com.microservice.discount.model.CategoryDto;
import com.microservice.discount.service.CategoryService;
import com.microservice.grpc.AllCategoryRequest;
import com.microservice.grpc.AllCategoryResponse;
import com.microservice.grpc.Category;
import com.microservice.grpc.CategoryServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;


@GrpcService
@RequiredArgsConstructor
public class CategoryGrpcServiceImpl extends CategoryServiceGrpc.CategoryServiceImplBase {

    private final CategoryService categoryService;

    @Override
    public void getAllCategoryByDiscount(AllCategoryRequest request, StreamObserver<AllCategoryResponse> responseObserver) {
        AllCategoryResp allCategories = categoryService.getAllCategories();
        List<CategoryDto> categoryList = allCategories.getCategoryList();

        List<Category> collect = categoryList.stream()
                .map(categoryDto -> {
                    return AllCategoryResponse.newBuilder().addCategoryBuilder().setName(categoryDto.getName()).setExternalId(categoryDto.getExternalId()).build();

                }).toList();

        responseObserver.onNext(
                AllCategoryResponse.newBuilder()
                        .addAllCategory(collect).build()
        );
        responseObserver.onCompleted();
    }

}
