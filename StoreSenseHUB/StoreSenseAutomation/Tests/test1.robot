*** Settings ***

Library  RequestsLibrary
Library  Collections
Library  String
Library  JSONLibrary

#Resource  /home/amit.saxena@capdomain.com/Final/Resources/keywrd.robot

Resource   Resources/keywrd.robot


*** Test Cases ***
device platform verifcation
    device platform verifcation

Add Device
    [Tags]  Device
    add device

Detach Device Using DeviceId
    [Tags]  Device
    detach device using deviceId

Detach Device By TillId and DeviceType
    [Tags]  Device
    detach device by tillId and deviceType

Upload Image
    [Tags]  Imageeee
    IMAGES
visitor data
    [Tags]  Image
    upload_visitor

create aws
    [Tags]  Certificate

    create aws cert

set_config_triger
    set_con

Image_invalid_scre
    [Tags]  Negative  image

    Image_INVALID_SCORE

negative_null
    [Tags]  Negative
    eventime_null
    log to console  eventime_null

image_empty_Scr
    [Tags]  Negative  image
    [Documentation]   FrameTime is Empty for Scenarios

    image_empty_score

Image_frame_time_invalid
    [Tags]  Negative  image
    [Documentation]   FrameTime is invalid for Scenarios

    Image_fra_time_invalid

Image_checksum_is_null
     [Tags]  Negative
     Image_checksum_null

Image_checksum_invalid
    [Tags]  Negative  image
    checksum_Invalid

Visitor_Invalid_Till_Id
    [Tags]  Negative  visitor
    visitor_invalid_till_id
Visitor_Invalid_Event_Time
    [Tags]  Negative  visitor
    visitor_invalid_event_time

Visitor_Invalid_Track_Id
    [Tags]  Negative  visitor
    visitor_invalid_track_id
Visitor_Invalid_Org_Id
    visitor_invalid_org_id

Visitor_Invalid_Device_Id
    visitor_invalid_device_id
Visitor_Invalid_Image_List
    visitor_invalid_image_list
Visitor_Invalid_Start_Time
    visitor_invalid_start_time

Visitor_null_store_id
   Visitor_null_sto_id

Visitor_till_ID_Null

    visitor_till_id_null

Visitor_store_id_null
     [Tags]  Negative
    Visitor_null_sto_id



frameTime_null
     [Tags]  Negative
    fra_time_null

mongodb
    [Tags]  mon
    mondb























