*** Settings ***
Library  RequestsLibrary
Library  Collections
Library  String
Library  JSONLibrary
Library  OperatingSystem
Library  negative.py
Resource  ./nightly.robot
Library  functions.py

*** Keywords ***



device platform verifcation
    create session   device platform verifcation     ${BASE_URL}     auth=@{test_auth}
    #url=generic_fun.BASE_URL, headers=generic_fun.test_auth,cookies={}, auth=None, timeout=None, proxies=None, verify=False,                     debug=0
    ${response}=  post request  device platform verifcation    ${dev_pla_veri_endpoint_url}    data=&{body}   headers=&{header}
    ${resp_body}=    convert to string  ${response.content}
    should contain    ${resp_body}    awsIotCertificate
    should contain    ${resp_body}    BEGIN CERTIFICATE REQUEST
    should contain    ${resp_body}    BEGIN PRIVATE KEY
    ${response_code}=  convert to string  ${response.status_code}
    should be equal   ${response_code}  200

detach device using deviceId
    create session   device    ${BASE_URL}    auth=@{test_auth}
    ${response}=   delete request  device   ${detach_device_using_deviceId_endpoint_url}    headers=&{header}
    ${resp_body}=    convert to string  ${response.content}
    ${response_code}=  convert to string  ${response.status_code}
    should be equal   ${response_code}  500
    log to console  ${Scalar_variable}
    log to console  ${response_code}
    log to console  ${response.content}

detach device by tillId and deviceType
    create session   device    ${BASE_URL}    auth=@{test_auth}
    ${response}=   delete request  device   ${discard_devcie_endpoint_url}
    ${resp_body}=    convert to string  ${response.content}
    ${response_code}=  convert to string  ${response.status_code}
    should be equal   ${response_code}  500
    log to console  ${Scalar_variable}
    log to console  ${response_code}
    log to console  ${response.content}

discard device

    create session   device    ${BASE_URL}    auth=@{test_auth}
    ${response}=   delete request  device   ${discard_devcie_endpoint_url}
    ${resp_body}=    convert to string  ${response.content}
    ${response_code}=  convert to string  ${response.status_code}
    should be equal   ${response_code}  500
    log to console  ${response_code}
    log to console  ${response.content}


IMAGES
     ${SEARCH} =       image_upload_data
     log to console    ${SEARCH}

upload_visitor
    ${data_for_visi}=       upload_visitor_data
    log to console

create aws cert
    ${cac} =   create_aws_certificate
    log to console  ${cac}

set_con
     ${set_congof_tr}=   set config
     log to console      ${set_congof_tr}


Image_INVALID_SCORE
     ${invalid_score}=    image_upload_data_score_invalid
image_empty_score
    ${invalid_score}=    image_upload_data_score_empty


Image_fra_time_invalid

    ${invad_frame_time}=   image_upload_data_frameTime_invalid

fra_time_null

    ${ft_null}=    image_upload_data_frameTime_null

Image_checksum_null

    ${chck_null}=    image_upload_data_checksum_null

checksum_Invalid

    ${invad_checksum}=   image_upload_data_checksum_invalid

visitor_invalid_till_id
    ${ivalid_tillid}=   upload_visitor_data_invalid_till_id

visitor_invalid_event_time
    ${invalid_tillid}=  upload_visitor_data_invalid_event_time

visitor_invalid_track_id
    ${invalid_track_id}=  upload_visitor_data_invalid_track_id
visitor_invalid_org_id
     ${invalid_org_id}=  upload_visitor_data_invalid_org_id
visitor_invalid_device_id
    ${invalid_device_id}=  upload_visitor_data_invalid_device_id
visitor_invalid_image_list
    ${invalid_image_list}=  upload_visitor_data_invalid_image_list
visitor_invalid_start_time
    ${invalid_start_time}=   upload_visitor_data_invalid_start_time

Visitor_null_sto_id
    ${st_id_null}=  upload_visitor_data_storeId_is_null

visitor_till_id_null

    ${nulltillid}=   upload_visitor_data_till_id_is_null

eventime_null

    ${nulltime}=     upload_visitor_data_event_time_is_null
    ${resp_body}=    convert to string  ${nulltime}
    log file  ${nulltime}
    log file  ${resp_body}


mondb
     mongodb_connect


















