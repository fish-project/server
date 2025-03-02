package src.grpcServer;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import src.grpcServer.gen.ConfirmEmailGrpc;
import src.grpcServer.gen.Request;
import src.grpcServer.gen.Response;
import src.model.ship;
import src.repository.ShipRepository;

@GrpcService
public class ConfirmEmailService extends ConfirmEmailGrpc.ConfirmEmailImplBase {
    @Autowired
    ShipRepository shipRepository;

    @Override
    public void checkEmail(Request request, StreamObserver<Response> responseObserver) {
        String result = "";
        try {
            String email = request.getEmail();
            String ship_id = request.getShipid();

            ship ship = shipRepository.findById(ship_id).orElseThrow(
                    Exception::new
            );
            
            if(ship.getShipOwner().equals(email)) {
                result = "success";
            } else {
                result = "fail";
            }
        } catch (Exception e) {
           result = "fail";
        } finally {
            Response response = Response.newBuilder().setMess(result).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
