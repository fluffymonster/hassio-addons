# WEEWX CONFIGURATION FILE
#
# Copyright (c) 2009-2021 Tom Keffer <tkeffer@gmail.com>
# See the file LICENSE.txt for your rights.

##############################################################################

# This section is for general configuration information.

# Set to 1 for extra debug info, otherwise comment it out or set to zero
debug = 0

# Root directory of the weewx data file hierarchy for this station
WEEWX_ROOT = /home/weewx

# Whether to log successful operations
log_success = {{ .log_success }}

# Whether to log unsuccessful operations
log_failure = {{ .log_failure }}

# How long to wait before timing out a socket (FTP, HTTP) connection
socket_timeout = 20

# Do not modify this. It is used when installing and updating weewx.
version = 4.5.1

##############################################################################

#   This section is for information about the station.

[Station]
    
    # Description of the station location
    location = {{ .Station_location }}
    
    # Latitude in decimal degrees. Negative for southern hemisphere
    latitude = {{ .Station_latitude }}
    # Longitude in decimal degrees. Negative for western hemisphere.
    longitude = {{ .Station_longitude }}
    
    # Altitude of the station, with unit it is in. This is downloaded from
    # from the station if the hardware supports it.
    altitude = {{ .Station_altitude }}    # Choose 'foot' or 'meter' for unit
    
    # Set to type of station hardware. There must be a corresponding stanza
    # in this file with a 'driver' parameter indicating the driver to be used.
    station_type = Simulator
    
    # If you have a website, you may specify an URL
    #station_url = http://www.example.com
    
    # The start of the rain year (1=January; 10=October, etc.). This is
    # downloaded from the station if the hardware supports it.
    rain_year_start = 1
    
    # Start of week (0=Monday, 6=Sunday)
    week_start = {{ .Station_start_of_week }}

##############################################################################

[Simulator]
    # This section is for the weewx weather station simulator
    
    # The time (in seconds) between LOOP packets.
    loop_interval = 2.5
    
    # The simulator mode can be either 'simulator' or 'generator'.
    # Real-time simulator. Sleep between each LOOP packet.
    mode = simulator
    # Generator.  Emit LOOP packets as fast as possible (useful for testing).
    #mode = generator
    
    # The start time. Format is YYYY-mm-ddTHH:MM. If not specified, the default 
    # is to use the present time.
    #start = 2011-01-01T00:00
    
    # The driver to use:
    driver = weewx.drivers.simulator

##############################################################################

#   This section is for uploading data to Internet sites

[StdRESTful]
    
    [[StationRegistry]]
        # To register this weather station with weewx, set this to true
        register_this_station = false
    
    [[AWEKAS]]
        # This section is for configuring posts to AWEKAS.
        
        # If you wish to do this, set the option 'enable' to true,
        # and specify a username and password.
        # To guard against parsing errors, put the password in quotes.
        enable = false
        username = replace_me
        password = replace_me
    
    [[CWOP]]
        # This section is for configuring posts to CWOP.
        
        # If you wish to do this, set the option 'enable' to true,
        # and specify the station ID (e.g., CW1234).
        enable = false
        station = replace_me
    
    # If this is an APRS (radio amateur) station, uncomment
    # the following and replace with a passcode (e.g., 12345).
    #passcode = replace_me (APRS stations only)
    
    [[PWSweather]]
        # This section is for configuring posts to PWSweather.com.
        
        # If you wish to do this, set the option 'enable' to true,
        # and specify a station and password.
        # To guard against parsing errors, put the password in quotes.
        enable = false
        station = replace_me
        password = replace_me
    
    [[WOW]]
        # This section is for configuring posts to WOW.
        
        # If you wish to do this, set the option 'enable' to true,
        # and specify a station and password.
        # To guard against parsing errors, put the password in quotes.
        enable = false
        station = replace_me
        password = replace_me
    
    [[Wunderground]]
        # This section is for configuring posts to the Weather Underground.
        
        # If you wish to do this, set the option 'enable' to true,
        # and specify a station (e.g., 'KORHOODR3') and password.
        # To guard against parsing errors, put the password in quotes.
        enable = false
        station = replace_me
        password = replace_me
        
        # If you plan on using wunderfixer, set the following
        # to your API key:
        api_key = replace_me
        
        # Set the following to True to have weewx use the WU "Rapidfire"
        # protocol. Not all hardware can support it. See the User's Guide.
        rapidfire = False
    [[MQTT]]
        server_url = {{ .MQTT_server_url }}
        topic = {{ .MQTT_topic }}
        unit_system = {{ .MQTT_unit_system }}
        aggregation = {{ .MQTT_aggregation }}

##############################################################################

#   This section specifies what reports, using which skins, to generate.

[StdReport]
    
    # Where the skins reside, relative to WEEWX_ROOT
    SKIN_ROOT = skins
    
    # Where the generated reports should go, relative to WEEWX_ROOT
    HTML_ROOT = ../../share/{{ .HTML_ROOT}}
    
    # The database binding indicates which data should be used in reports.
    data_binding = wx_binding
    
    # Whether to log a successful operation
    log_success = True
    
    # Whether to log an unsuccessful operation
    log_failure = True
    
    # Each of the following subsections defines a report that will be run.
    # See the customizing guide to change the units, plot types and line
    # colors, modify the fonts, display additional sensor data, and other
    # customizations. Many of those changes can be made here by overriding
    # parameters, or by modifying templates within the skin itself.
    
    [[SeasonsReport]]
        # The SeasonsReport uses the 'Seasons' skin, which contains the
        # images, templates and plots for the report.
        skin = Seasons
        enable = true
    
    [[SmartphoneReport]]
        # The SmartphoneReport uses the 'Smartphone' skin, and the images and
        # files are placed in a dedicated subdirectory.
        skin = Smartphone
        enable = false
        HTML_ROOT = public_html/smartphone
    
    [[MobileReport]]
        # The MobileReport uses the 'Mobile' skin, and the images and files
        # are placed in a dedicated subdirectory.
        skin = Mobile
        enable = false
        HTML_ROOT = public_html/mobile
    
    [[StandardReport]]
        # This is the old "Standard" skin. By default, it is not enabled.
        skin = Standard
        enable = false
    
    [[FTP]]
        # FTP'ing the results to a webserver is treated as just another report,
        # albeit one with an unusual report generator!
        skin = Ftp
        
        # If you wish to use FTP, set "enable" to "true", then
        # fill out the next four lines.
        # Use quotes around passwords to guard against parsing errors.
        enable = false
        user = replace_me
        password = replace_me
        server = replace_me    # The ftp server name, e.g, www.myserver.org
        path = replace_me    # The destination directory, e.g., /weather
        
        # Set to True for an FTP over TLS (FTPS) connection. Not all servers
        # support this.
        secure_ftp = False
        
        # To upload files from something other than what HTML_ROOT is set
        # to above, specify a different HTML_ROOT here.
        #HTML_ROOT = public_html
        
        # Most FTP servers use port 21
        port = 21
        
        # Set to 1 to use passive mode, zero for active mode
        passive = 1
    
    [[RSYNC]]
        # rsync'ing to a webserver is treated as just another report
        skin = Rsync
        
        # If you wish to use rsync, you must configure passwordless ssh using
        # public/private key authentication from the user account that weewx
        # runs to the user account on the remote machine where the files
        # will be copied.
        #
        # If you wish to use rsync, set "enable" to "true", then
        # fill out server, user, and path.
        # The server should appear in your .ssh/config file.
        # The user is the username used in the identity file.
        # The path is the destination directory, such as /var/www/html/weather.
        # Be sure that the user has write permissions on the destination!
        enable = {{ .RSYNC_enable }}
        server = {{ .RSYNC_server }}
        user = {{ .RSYNC_user }}
        path = {{ .RSYNC_path }}
        
        # To upload files from something other than what HTML_ROOT is set
        # to above, specify a different HTML_ROOT here.
        #HTML_ROOT = public_html
        
        # Rsync can be configured to remove files from the remote server if
        # they don't exist under HTML_ROOT locally. USE WITH CAUTION: if you
        # make a mistake in the remote path, you could could unintentionally
        # cause unrelated files to be deleted. Set to 1 to enable remote file
        # deletion, zero to allow files to accumulate remotely.
        delete = 0
    
    ####
    
    # Various options for customizing your reports.
    
    [[Defaults]]
        
        [[[Units]]]
            
            # The following section sets what unit to use for each unit group.
            # NB: The unit is always in the singular. I.e., 'mile_per_hour',
            # NOT 'miles_per_hour'
            [[[[Groups]]]]
                
                group_altitude = meter    # Options are 'foot' or 'meter'
                group_degree_day = degree_C_day    # Options are 'degree_F_day' or 'degree_C_day'
                group_distance = km    # Options are 'mile' or 'km'
                group_pressure = mbar    # Options are 'inHg', 'mmHg', 'mbar', 'hPa', or 'kPa'
                group_rain = mm    # Options are 'inch', 'cm', or 'mm'
                group_rainrate = mm_per_hour    # Options are 'inch_per_hour', 'cm_per_hour', or 'mm_per_hour'
                group_speed = meter_per_second    # Options are 'mile_per_hour', 'km_per_hour', 'knot', or 'meter_per_second'
                group_speed2 = meter_per_second2    # Options are 'mile_per_hour2', 'km_per_hour2', 'knot2', or 'meter_per_second2'
                group_temperature = degree_C    # Options are 'degree_F' or 'degree_C'
            
            # The following section sets the formatting for each type of unit.
            [[[[StringFormats]]]]
                
                centibar = %.0f
                cm = %.2f
                cm_per_hour = %.2f
                degree_C = %.1f
                degree_F = %.1f
                degree_compass = %.0f
                foot = %.0f
                hPa = %.1f
                hour = %.1f
                inHg = %.3f
                inch = %.2f
                inch_per_hour = %.2f
                km = %.1f
                km_per_hour = %.0f
                km_per_hour2 = %.1f
                knot = %.0f
                knot2 = %.1f
                kPa = %.2f
                mbar = %.1f
                meter = %.0f
                meter_per_second = %.1f
                meter_per_second2 = %.1f
                mile = %.1f
                mile_per_hour = %.0f
                mile_per_hour2 = %.1f
                mm = %.1f
                mmHg = %.1f
                mm_per_hour = %.1f
                percent = %.0f
                second = %.0f
                uv_index = %.1f
                volt = %.1f
                watt_per_meter_squared = %.0f
                NONE = "   N/A"
            
            # The following section overrides the label used for each type of unit
            [[[[Labels]]]]
                
                meter = " meter", " meters"    # You may prefer "metre".
                day = " day", " days"
                hour = " hour", " hours"
                minute = " minute", " minutes"
                second = " second", " seconds"
                NONE = ""
            
            # The following section sets the format for each time scale.
            # The values below will work in every locale, but they may not look
            # particularly attractive.
            [[[[TimeFormats]]]]
                
                hour = %H:%M
                day = %X
                week = %X (%A)
                month = %x %X
                year = %x %X
                rainyear = %x %X
                current = %x %X
                ephem_day = %X
                ephem_year = %x %X
            
            [[[[Ordinates]]]]
                
                # Ordinal directions. The last one is for no wind direction
                directions = N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW, N/A
            
            # The following section sets the base temperatures used for the
            #  calculation of heating, cooling, and growing degree-days.
            [[[[DegreeDays]]]]
                
                # Base temperature for heating days, with unit:
                heating_base = 65, degree_F
                # Base temperature for cooling days, with unit:
                cooling_base = 65, degree_F
                # Base temperature for growing days, with unit:
                growing_base = 50, degree_F
            
            # A trend takes a difference across a time period. The following
            # section sets the time period, and how big an error is allowed to
            # still be counted as the start or end of a period.
            [[[[Trend]]]]
                
                time_delta = 10800    # 3 hours
                time_grace = 300    # 5 minutes
        
        # The labels to be used for each observation type
        [[[Labels]]]
            
            # Set to hemisphere abbreviations suitable for your location:
            hemispheres = N, S, E, W
            
            # Formats to be used for latitude whole degrees, longitude whole
            # degrees, and minutes:
            latlon_formats = %02d, %03d, %05.2f
            
            # Generic labels, keyed by an observation type.
            [[[[Generic]]]]
                barometer = Barometer
                dewpoint = Dew Point
                ET = ET
                heatindex = Heat Index
                inHumidity = Inside Humidity
                inTemp = Inside Temperature
                outHumidity = Humidity
                outTemp = Outside Temperature
                radiation = Radiation
                rain = Rain
                rainRate = Rain Rate
                UV = UV Index
                windDir = Wind Direction
                windGust = Gust Speed
                windGustDir = Gust Direction
                windSpeed = Wind Speed
                windchill = Wind Chill
                windgustvec = Gust Vector
                windvec = Wind Vector
                extraTemp1 = Temperature1
                extraTemp2 = Temperature2
                extraTemp3 = Temperature3
                
                # Sensor status indicators
                
                rxCheckPercent = Signal Quality
                txBatteryStatus = Transmitter Battery
                windBatteryStatus = Wind Battery
                rainBatteryStatus = Rain Battery
                outTempBatteryStatus = Outside Temperature Battery
                inTempBatteryStatus = Inside Temperature Battery
                consBatteryVoltage = Console Battery
                heatingVoltage = Heating Battery
                supplyVoltage = Supply Voltage
                referenceVoltage = Reference Voltage
        
        [[[Almanac]]]
            
            # The labels to be used for the phases of the moon:
            moon_phases = New, Waxing crescent, First quarter, Waxing gibbous, Full, Waning gibbous, Last quarter, Waning crescent

##############################################################################

#   This service acts as a filter, converting the unit system coming from
#   the hardware to a unit system in the database.

[StdConvert]
    
    # The target_unit affects only the unit system in the database. Once
    # chosen it cannot be changed without converting the entire database.
    # Modification of target_unit after starting weewx will result in
    # corrupt data - the database will contain a mix of US and METRIC data.
    #
    # The value of target_unit does not affect the unit system for
    # reporting - reports can display US, Metric, or any combination of units.
    #
    # In most cases, target_unit should be left as the default: US
    #
    # In particular, those migrating from a standard wview installation
    # should use US since that is what the wview database contains.
    
    # DO NOT MODIFY THIS VALUE UNLESS YOU KNOW WHAT YOU ARE DOING!
    target_unit = US    # Options are 'US', 'METRICWX', or 'METRIC'

##############################################################################

#   This section can adjust data using calibration expressions.

[StdCalibrate]
    
    [[Corrections]]
        # For each type, an arbitrary calibration expression can be given.
        # It should be in the units defined in the StdConvert section.
        # Example:
        foo = foo + 0.2

##############################################################################

#   This section is for quality control checks. If units are not specified,
#   values must be in the units defined in the StdConvert section.

[StdQC]
    
    [[MinMax]]
        barometer = 26, 32.5, inHg
        pressure = 24, 34.5, inHg
        outTemp = -40, 120, degree_F
        inTemp = 10, 120, degree_F
        outHumidity = 0, 100
        inHumidity = 0, 100
        windSpeed = 0, 120, mile_per_hour
        rain = 0, 10, inch

##############################################################################

#   This section controls the origin of derived values.

[StdWXCalculate]
    
    [[Calculations]]
        # How to calculate derived quantities.  Possible values are:
        #  hardware        - use the value provided by hardware
        #  software        - use the value calculated by weewx
        #  prefer_hardware - use value provide by hardware if available,
        #                      otherwise use value calculated by weewx
        
        pressure = prefer_hardware
        altimeter = prefer_hardware
        appTemp = prefer_hardware
        barometer = prefer_hardware
        cloudbase = prefer_hardware
        dewpoint = prefer_hardware
        ET = prefer_hardware
        heatindex = prefer_hardware
        humidex = prefer_hardware
        inDewpoint = prefer_hardware
        maxSolarRad = prefer_hardware
        rainRate = prefer_hardware
        windchill = prefer_hardware
        windrun = prefer_hardware

##############################################################################

#   For hardware that supports it, this section controls how often the
#   onboard clock gets updated.

[StdTimeSynch]
    
    # How often to check the weather station clock for drift (in seconds)
    clock_check = 14400
    
    # How much it can drift before we will correct it (in seconds)
    max_drift = 5

##############################################################################

#   This section is for configuring the archive service.

[StdArchive]
    
    # If the station hardware supports data logging then the archive interval
    # will be downloaded from the station. Otherwise, specify it (in seconds).
    archive_interval = 300
    
    # If possible, new archive records are downloaded from the station
    # hardware. If the hardware does not support this, then new archive
    # records will be generated in software.
    # Set the following to "software" to force software record generation.
    record_generation = hardware
    
    # Whether to include LOOP data in hi/low statistics
    loop_hilo = True
    
    # The data binding used to save archive records
    data_binding = wx_binding
    
    # Whether to log successful archive operations
    log_success = True
    
    # Whether to log unsuccessful archive operations
    log_failure = True

##############################################################################

#   This section binds a data store to a database.

[DataBindings]
    
    [[wx_binding]]
        # The database must match one of the sections in [Databases].
        # This is likely to be the only option you would want to change.
        database = archive_sqlite
        # The name of the table within the database
        table_name = archive
        # The manager handles aggregation of data for historical summaries
        manager = weewx.manager.DaySummaryManager
        # The schema defines the structure of the database.
        # It is *only* used when the database is created.
        schema = schemas.wview_extended.schema

##############################################################################

#   This section defines various databases.

[Databases]
    
    # A SQLite database is simply a single file
    [[archive_sqlite]]
        database_name = weewx.sdb
        database_type = SQLite
    
    # MySQL
    [[archive_mysql]]
        database_name = weewx
        database_type = MySQL

##############################################################################

#   This section defines defaults for the different types of databases.

[DatabaseTypes]
    
    # Defaults for SQLite databases
    [[SQLite]]
        driver = weedb.sqlite
        # Directory in which the database files are located
        SQLITE_ROOT = /data
    
    # Defaults for MySQL databases
    [[MySQL]]
        driver = weedb.mysql
        # The host where the database is located
        host = localhost
        # The user name for logging in to the host
        user = weewx
        # The password for the user name (quotes guard against parsing errors)
        password = weewx

##############################################################################

#   This section configures the internal weewx engine.

[Engine]
    
    # The following section specifies which services should be run and in what order.
    [[Services]]
        prep_services = weewx.engine.StdTimeSynch
        data_services = ,
        process_services = weewx.engine.StdConvert, weewx.engine.StdCalibrate, weewx.engine.StdQC, weewx.wxservices.StdWXCalculate
        xtype_services = weewx.wxxtypes.StdWXXTypes, weewx.wxxtypes.StdPressureCooker, weewx.wxxtypes.StdRainRater, weewx.wxxtypes.StdDelta
        archive_services = weewx.engine.StdArchive
        restful_services = weewx.restx.StdStationRegistry, weewx.restx.StdWunderground, weewx.restx.StdPWSweather, weewx.restx.StdCWOP, weewx.restx.StdWOW, weewx.restx.StdAWEKAS, user.mqtt.MQTT
        report_services = weewx.engine.StdPrint, weewx.engine.StdReport

