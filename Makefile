PROTOC = protoc
PLUGIN = --plugin=protoc-gen-grpc-java=/usr/local/bin/protoc-gen-grpc-java
JAVA_OUT = src/main/java
PROTO_FILE = src/main/java/src/grpcServer/grpc/main.proto

gen:
	@echo "Generating gRPC Java code from $(PROTO_FILE)..."
	protoc --java_out=$(JAVA_OUT) --grpc-java_out=$(JAVA_OUT)  $(PROTO_FILE)

clean:
	rm -rf $(JAVA_OUT)/src/grpcServer/gen/*.java

.PHONY: clean gen
