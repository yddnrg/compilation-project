FROM frolvlad/alpine-gxx
COPY test.cpp ./
WORKDIR /app/
RUN g++ test.cpp -o test
RUN chmod +x test
