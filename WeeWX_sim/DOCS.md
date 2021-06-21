# Home Assitant Add-on: WeeWX Sumulator

## Installation

Follow these steps to get the add-on installed on your system:

1. Navigate in your Home Assistant frontend to **Supervisor** -> **Add-on Store**.
2. Find the "WeeWX Sim" add-on and click it.
3. Click on the "INSTALL" button.

## How to use

The add-on has a couple of options available. For more detailed instructions
see below. The basic thing to get the add-on running would be:

1. Start the add-on.

## Configuration

Configuration for WeeWX simulator mode only

Example add-on configuration:

```yaml
Station:
  location:
  latitude:
  longitude:
  altitude:
  start_of_week:
RSYNC:
  enable:
  server:
  user:
  password:
  path:
Logging:
  log_sucess:
  log_failure:
MQTT:
  server_url:
  topic:
  unit_system:
  aggregation:
Skins:
  date_format: '%d-%b-%Y %H:%M'
Maintenance: false
```

### Option: 'Station' (required)
Station configurations

#### location

### Option: 'RSYNC' (required)
When enabled, will send the Seasons report generated to the configured path

#### enable

#### server

#### user

#### password

#### path

### Option: 'Logging' (required)

### Option: 'MQTT' (required)

### Option: 'Skins' (required)

#### date_format
Format the date string for Seasons report

### Option: 'Maintenance' (required)

Setting to true will prevent weewxd from executing but will old the container open. Useful for running weewxd in foreground or if you want to access the database file.


