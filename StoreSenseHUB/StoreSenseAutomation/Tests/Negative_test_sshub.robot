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
    [Tags]  Image
    IMAGES
visitor data
    [Tags]  Image
    upload_visitor

create aws
    [Tags]  Certificate

    create aws cert

set_config_triger
    set_con


