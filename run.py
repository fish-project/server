import subprocess
import argparse
import os

def generate_grpc(name:str):
    """
    Chạy lệnh protoc để sinh code gRPC cho Java.
    """
    command = [
        "protoc",
        "--grpc-java_out=./",
        "--plugin=protoc-gen-grpc-java=/usr/local/bin/protoc-gen-grpc-java",
        f"./src/main/java/grpc/{name}.proto"
    ]

    try:
        subprocess.run(command, check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error: msg: {e}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate gRPC")
    parser.add_argument("-name", required=True, help="Name of the service being generated")

    args = parser.parse_args()

    if not os.path.exists(f"src/main/java/grpc/{args.name}.proto"):
        print(f"❌ Error: .proto file '{args.name}' not found!")
        exit(1)

    generate_grpc(args.name)
