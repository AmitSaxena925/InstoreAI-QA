*** Settings ***
Library  RequestsLibrary
Library  Collections
Library  String
Library  JSONLibrary
Library  OperatingSystem
#Library  ./generic_fun.py



*** Variables ***

${BASE_URL}=    https://nightly.capillary.in
@{test_auth} =      cvp.instoreai1    202cb962ac59075b964b07152d234b70
&{header}  Content-Type=application/json
&{body}  tillName= cvp.instoreai1
&{params}  deviceType= ce
#@temp
#&{validation_part}  code=200    registrationState=Completed
#&{validation_pa}   convert to dictionary     &{validation_part}
${json_path}=    /home/amit.saxena@capdomain.com/PycharmProjects/CAPILLARY_REST_API_TESTING/REST_API_TESTING/Common_Resources/Json.json
${dev_pla_veri_endpoint_url}=   /instoreai/storesense/storesense-hub/device/platform/202481595999429/datasource/?deviceType=ce
${Scalar_variable} =  !!!!!!!!!!!!!! One Test Case Completed !!!!!!!!!!!!!!!!!!!!
${SEARCH} =  time_module
${image_file_path}=  /home/amit.saxena@capdomain.com/Downloads/second.png
${log_file_path}=    /home/amit.saxena@capdomain.com/PycharmProjects/CAPILLARY_REST_API_TESTING/REST_API_TESTING/Common_Resources/Test_log
${create_session}=   Device


#***************************************************************** Add Device Variables ******************************************************************
${add_device_endpoint_url}=  /instoreai/storesense/storesense-hub/device/platform/201907171834016/datasource?deviceType=CE
&{body}  tillName= cvp.instoreai1
&{header}  Content-Type=application/json

# detach device using deviceId
${detach_device_using_deviceId_endpoint_url}   /instoreai/storesense/storesense-hub/device/platform/201907171834018/datasource
&{header}  Content-Type=application/json
&{image_lo}  urlsss=https://nightly.capillary.in/instoreai/storesense/storesense-hub/image/151108/200180212/200180213/    files={'image': open('/home/amit.saxena@capdomain.com/Downloads/second.png', 'rb')}    datas={'checksum': 'c45f4b056483ed50b22eb5afd281c4f6', 'frameTime': '1576146785', 'score': '80'}
# Detach device by tillId and deviceType
${detach_device_by_tillid_deviceType_endpoint_url}  /instoreai/storesense/storesense-hub/till/platform/50013158?deviceType=CE
# discard device
${discard_devcie_endpoint_url}  /instoreai/storesense/storesense-hub/device/platform/201904011256033
# upload Image
${end_point}    /instoreai/storesense/storesense-hub/image/151108/200180212/200180213/
${urlss}=  https://nightly.capillary.in/instoreai/storesense/storesense-hub/image/151108/200180212/200180213/
#${urlss}=    https://nightly.capillary.in/instoreai/storesense/storesense-hub/image/151108/200180212/200180213/
${files}=  create dictionary    image=open(${filname}, 'rb')
#&{datas}= create dic    checksum=${SEARCH}  frameTime=${mili_sec}   score=80
${filname}=  /home/amit.saxena@capdomain.com/Downloads/second.png
#${datas}=  create dictionary     checksum=${SEARCH}     frameTime= ${mili_sec}   score= 80
@{ScoreList}=    Create List
#filename = /home/amit.saxena@capdomain.com/Downloads/second.png
#base_url = 'https://nightly.capillary.in/instoreai/storesense/storesense-hub/image/151108/200180212/200180213/'
# get certificate
${get_certificate_endpoint}=  /instoreai/storesense/storesense-hub/device/iot/12856946?deviceType=CE
# upload visitor dat
@{image all data}  create list    5df2196440f1dd488ebf9707  5df21f4e40f1dd488ebf993b  5df21fed40f1dd488ebf9984  5df2203140f1dd488ebf99a5  5df22069ce2d4648bbf8129d
${upload_visitor_endpoint}=   /instoreai/storesense/storesense-hub/visitor
#${var2} = time_module
#${body}=  create dictionary    tillId=50015469    eventTime=${SEARCH}   trackId=1563340861000-50013159    storeId=50014457    orgId=50303  deviceId=2019031510424000    startTime=${var2}   eventType=out    imageList=@{image all data}
#${json_valu}=  convert to dictionary  ${body}



