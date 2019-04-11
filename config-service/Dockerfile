FROM golang:1.9.1

ENV GOPATH /go

FROM golang:1.9.1
COPY domain /go/src/app/domain
COPY controller /go/src/app/controller
COPY service /go/src/app/service

WORKDIR /go/src/app

RUN go get -d -v ./...
RUN go install -v ./...

CMD ["app"]
ENTRYPOINT ["app", "-f=7", "-s=9"]