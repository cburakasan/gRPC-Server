package com.microservice.discount.service.grpc;


import com.microservice.discount.mapper.GrpcMapper;
import com.microservice.discount.model.DiscountReq;
import com.microservice.discount.model.DiscountResp;
import com.microservice.discount.service.DiscountProcessService;
import com.microservice.grpc.DiscountRequest;
import com.microservice.grpc.DiscountResponse;
import com.microservice.grpc.DiscountServiceGrpc;
import com.microservice.grpc.Response;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GrpcService
@RequiredArgsConstructor
public class DiscountGrpcServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase{

    private final DiscountProcessService discountProcessService;

    private static final Logger logger = LoggerFactory.getLogger(DiscountGrpcServiceImpl.class);

    @Override
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {


        DiscountReq discountReq = GrpcMapper.INSTANCE.discountRequestToDiscountReq(request);

        DiscountResp discountByCategory = discountProcessService.getDiscountByCategory(discountReq);


        responseObserver.onNext(
                DiscountResponse.newBuilder()
                        .setCode(discountByCategory.getDiscount().getCode())
                        .setOldPrice(discountByCategory.getOldPrice().floatValue())
                        .setNewPrice(discountByCategory.getNewPrice().floatValue())
                        .setResponse(
                                Response.newBuilder().setStatusCode(true)
                                        .setMessage("Discount has been applied succesfuly")
                                        .build()
                        )
                        .build()
        );
        responseObserver.onCompleted();
        //super.getDiscount(request, responseObserver);
    }

}
