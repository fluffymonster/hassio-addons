ARG BUILD_FROM
FROM $BUILD_FROM

ENV LANG C.UTF-8

# Copy data for add-on
COPY run.sh /

#RUN wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/v1.51.0/rclone-v1.51.0-linux-arm.zip 
RUN apk add --no-cache unzip findutils && \
    wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/v1.51.0/rclone-v1.51.0-linux-arm.zip  && \
    unzip /tmp/rclone.zip -d /tmp && \
    cp /tmp/rclone-v1.51.0-linux-arm/rclone /usr/bin/rclone && \
    chmod a+x /usr/bin/rclone && \
    chmod a+x /run.sh && \
    rm -rf /tmp/*
CMD [ "/run.sh" ]