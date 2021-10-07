FROM frolvlad/alpine-gxx
WORKDIR /app/
COPY test.cpp ./
RUN g++ test.cpp -o test
RUN chmod +x test
