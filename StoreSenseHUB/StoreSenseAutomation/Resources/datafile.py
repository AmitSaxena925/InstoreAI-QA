import json
# ************************************Image Upload data*********************************************************

with open("config.json","r") as filename:

    distros_dict = json.load(filename)
    url = distros_dict["base_url"]
    body='body'
    auth_id =  str(distros_dict["auth_usename"]) , str(distros_dict["auth_password"])
    #print(auth_id)
    end_point_iu = distros_dict["Endpoint_Image_Upload"]
    qury_par_imge_up = distros_dict["id1"]
    qury_par_imge_up_1 = distros_dict["id2"]
    qury_par_imge_up_2 = distros_dict["id3"]

    path_of_file = [distros_dict["filepath1"],
                    distros_dict["filepath2"],
                    distros_dict["filepath3"],
                    distros_dict["filepath4"],
                    distros_dict["filepath5"]
]
    # pass image_url endpoint
    pa = distros_dict["filepath1"]
    end_iu = str(end_point_iu) + str(qury_par_imge_up) + '/' + str(qury_par_imge_up_1) + '/' + str(qury_par_imge_up_2)

    # pass visitor_upload endpoint
    end_point_vud = distros_dict["Endpoint_Visitor_Upload"]
    till_id=distros_dict["tillId"]
    eve_tme = distros_dict["eventTime"]
    #trackId= distros_dict["trackId"]
    storeId = distros_dict["storeId"],
    orgId = distros_dict["orgId"],
    deviceId = distros_dict["deviceId"],
    eventType = distros_dict["eventType"]
    end_point_add_device = distros_dict["Endpoint_Add_device"]
    add_device_par = str(distros_dict["add_device_id1"]) + '/'+ distros_dict["add_device_id2"]
    device_type = distros_dict["devicetype"]+ '=' + distros_dict["devicetype_id"]
    #till_name = dict(str(distros_dict["tillName"])+':'+str(distros_dict["tillName_value"]))
    #v =print(type((till_name)))

    # pass add device  endpoint
    #end_add_device = str(end_point_add_device) + str(add_device_par)+ '?' + str(device_type) #+ str(till_name)
    #print(end_add_device)

    ventType = distros_dict["eventType"]
    end_point_add_device = distros_dict["Endpoint_Add_device"]
    add_device_id1 = distros_dict["add_device_id1"]
    add_device_id2  = distros_dict["add_device_id2"]
    device_type = distros_dict["devicetype"] + '=' + distros_dict["devicetype_id"]
    # print(device_type)
    till_name = str(distros_dict["tillName"])
    till_value = str(distros_dict["tillName_value"])
    # v={"till_name"+':'+"till_value"}
    v = '{"tillname":"ce"}'
    # print(type(v))

    # pass add device  endpoint
    end_add_device = str(add_device_par) + '?' #+ str(device_type)  # + str(till_name)
    # pass detach device endpoint
    devicetype_id = distros_dict["devicetype_id"]
    end_point_detach = distros_dict["Endpoint_detach_device"]
    # endpoint "detach_by_tillid_devcie_type"
    detach_id1 = distros_dict["detach_by_tillid"]
    detach_by_tillid_endpoint = distros_dict["detach_by_tillid_devcie_type"]
    #print(detach_by_tillid_endpoint)
    data_detach_by_tillid = str(detach_id1) #+ str(deviceId) + str(devicetype_id)
    aws_cer = distros_dict["aws_cer"]
    aws_endpoint = str(distros_dict["Endpoint_aws_certificate"]) + str(distros_dict["aws_id"]) + '/' + str(aws_cer) + '?' + str(device_type)
    # set config endpoint
    scid1 = distros_dict["set_config_id"]
    scid2 = distros_dict["set_config_id1"]
    endpoint_set_config = distros_dict["Endpoint_set_config"]
    #print(endpoint_set_config)
    #var=scid1 +'/'+scid2
    #print(var)

    se_cfig_parm = distros_dict["till"] + '=' + distros_dict["tillId"]
    stro = distros_dict["store"] + '=' + distros_dict["storeId"]
    org = distros_dict["org"] + '=' + distros_dict["orgId"]
    set_query_pa = scid1 + '/' + scid2
    se = {scid1, scid2}
    set_config_par ={distros_dict["till"] :distros_dict["tillId"] ,distros_dict["store"]:distros_dict["storeId"],distros_dict["org"]: distros_dict["orgId"],distros_dict["devicetype"]:distros_dict["devicetype_id"]}
    #print(set_config_par)

    print(se)

    auth = ('cvp.instoreai1', '202cb962ac59075b964b07152d234b70')

# 50015469 / config?tillId = 50015469 & storeId = 50014457 & orgId = 50303 & deviceType = ce
# 50015469/config?tillId=50013159&stroeId=50013056&orgid=794&deviceType=ce


#print(url,auth_id,end_point,qury_par_imge_up,qury_par_imge_up_1,qury_par_imge_up_2)


















































'''



detach_by_tillid_devcie_type_endpoint = ans1 #'/instoreai/storesense/storesense-hub/till/platform/50013158?deviceType=CE'
b_url= ans                                                                        # 'https://nightly.capillary.in'
path_of_file =['/home/amit.saxena@capdomain.com/Downloads/first.jpeg','/home/amit.saxena@capdomain.com/Downloads/first.jpeg','/home/amit.saxena@capdomain.com/Downloads/third.png','/home/amit.saxena@capdomain.com/Downloads/fourth.jpeg','/home/amit.saxena@capdomain.com/Downloads/fifth.png']
#base_url = 'https://nightly.capillary.in/instoreai/storesense/storesense-hub/image/151108/200180212/200180213/'
en = str(ans6)+str(ans3)+'/' + str(ans4) +'/' + str(ans5)
#data={sheet.cell_value(3,1):sheet.cell_value(3,2),sheet.cell_value(4,1):sheet.cell_value(4,2),sheet.cell_value(5,1):sheet.cell_value(5,2)}
auth=('cvp.instoreai1', '202cb962ac59075b964b07152d234b70')
temp=[]
BASE_URL='https://nightly.capillary.in/instoreai/storesense/storesense-hub/device/platform/202481595999429/datasource/?deviceType=ce'
# body_
device_platform_verifcation_body ={'tillName':'cvp.instoreai1'}
test_auth = ['cvp.instoreai1','202cb962ac59075b964b07152d234b70']
headers = {'Content-Type':'application/json'}
#endpoints = '/instoreai/storesense/storesense-hub/device/platform/202481595999429/datasource/?deviceType=ce'
tillId='50013159'
eventTime='1563340861'
trackId='1563340861000-60013170'
storeId='50013056'
orgId='794'
deviceId='201907101218044'
eventType='in'
# *********************************************Add Device Functions ************************************************
Url = 'https://nightly.capillary.in/instoreai/storesense/storesense-hub/device/platform/201907171834016/datasource?deviceType=ce'
#endpoint ='/instoreai/storesense/storesense-hub/device/platform/201907171834016/datasource?deviceType=CE'
add_device_body = {'tillName':'cvp.instoreai1'}
# *********************************Detach device using deviceId ******************************************************
detach_devcie_url ='https://nightly.capillary.in/instoreai/storesense/storesense-hub/device/platform/201907171834018/datasource'
detach_devcie_endpoint ='instoreai/storesense/storesense-hub/device/platform/201907171834018/datasource'
# *******************************detach device by tillId and deviceType ***********************************************
detach_by_tillid_devcie_types = b_url + detach_by_tillid_devcie_type_endpoint
#print(detach_by_tillid_devcie_type)
detach_by_tillid_devcie_type_endpoint = '/instoreai/storesense/storesense-hub/till/platform/{id}'
data={"deviceType":"CE","id":"50013158"}

#****************************** aws ***************************
#create_aws_endpoint= "/instoreai/storesense/storesense-hub/device/iot/{id1}"

aws_data ={"deviceType":"CE","id":"12856946"}



#aws_endpoint= str(ans9)+str(ans10)+'?' + str(ans11)+ '='+str(ans12)


# ********* Image Upload apis********************************************************************
with open("Nightly_Config.json",'r') as filename:
'''