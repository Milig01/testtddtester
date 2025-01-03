FROM ubuntu:latest
LABEL authors="mig"

ENTRYPOINT ["top", "-b"]