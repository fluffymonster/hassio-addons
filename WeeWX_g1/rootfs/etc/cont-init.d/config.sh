#!/usr/bin/with-contenv bashio
# ==============================================================================
# WeeWX_g1 config
# ==============================================================================

CONFIG="/home/weewx/weewx.conf"
MQTT_URL=$(bashio::config 'MQTT.server_url')
RSYNC_URL="$(bashio::config 'RSYNC.user')@$(bashio::config 'RSYNC.server')"

bashio::log.info "Configuring KeyGen.."
if bashio::fs.file_exists "~/.ssh/id_rsa" \
  && bashio::fs.file_exists "~/.ssh/id_rsa.pub";
then
  bashio::log.info "KeyGen exists"
else
  bashio::log.info "Generate KeyGen"
  ssh-keygen -q -t rsa -N '' <<< ""$'\n'"y" >/dev/null 2>&1
fi
if bashio::fs.file_exists "~/.ssh/known_hosts"
then
	rm ~/.ssh/known_hosts
fi

if bashio::config.has_value "RSYNC.password"
# we should test if RSYNC.password is set or report error at least
then
	bashio::log.info "Store RSYNC key"
	sshpass -p "$(bashio::config 'RSYNC.password')" ssh-copy-id -o StrictHostKeyChecking=no ${RSYNC_URL} >/dev/null 2>&1
else
	bashio::log.info "No RSYNC password configured"
fi

bashio::log.info "Configuring WeeWX Service.."

bashio::var.json \
	Station_location "$(bashio::config 'Station.location')" \
	Station_latitude "$(bashio::config 'Station.latitude')" \
	Station_longitude "$(bashio::config 'Station.longitude')" \
	Station_altitude "$(bashio::config 'Station.altitude')" \
	Station_start_of_week "^$(bashio::config 'Station.start_of_week')" \
	MQTT_server_url "$MQTT_URL" \
	MQTT_topic "$(bashio::config 'MQTT.topic')" \
	MQTT_unit_system  "$(bashio::config 'MQTT.unit_system')" \
	MQTT_aggregation "$(bashio::config 'MQTT.aggregation')" \
	log_success "^$(bashio::config 'Logging.log_success')" \
	log_failure "^$(bashio::config 'Logging.log_failure')" \
	RSYNC_enable "^$(bashio::config 'RSYNC.enable')" \
	RSYNC_server "$(bashio::config 'RSYNC.server')" \
	RSYNC_user "$(bashio::config 'RSYNC.user')" \
	RSYNC_path "$(bashio::config 'RSYNC.path')" \
	HTML_ROOT "$(bashio::config 'Web.HTML_ROOT')" \
	| tempio \
     -template /usr/share/tempio/weewx.conf.gtpl \
     -out "${CONFIG}"

bashio::log.info "Configuring WeeWX Skins"

bashio::var.json \
	Skins_date_format "$(bashio::config 'Skins.date_format')" \
	| tempio \
     -template /usr/share/tempio/skin.Seasons.titlebar.gtpl \
     -out /home/weewx/skins/Seasons/titlebar.inc

