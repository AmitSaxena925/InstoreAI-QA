import datetime
import hashlib
import smtplib
import time
from email import encoders
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

import requests

from Resources.negative_data import *


def calculate_checksum(filename):
    with open(filename, 'rb') as open_file:
        content = open_file.read()
        hasher = hashlib.md5()
        hasher.update(content)
        return (hasher.hexdigest())

def time_module():
    local_time = time.mktime(datetime.datetime.now().timetuple())
    return int(local_time)
with_out_mili =time_module()

def time_module_mili():
    millis = int(round(time.time() * 1000))
    return int(millis)

va = time_module_mili()

temp=[]
def image_upload_data():

    for i in path_of_file:
        var = calculate_checksum(i)
        var2 = time_module_mili()
        urlss = str(url) + str(end_iu)
        files = {'image': open(i, 'rb')}
        datas = {'checksum': var, 'frameTime': with_out_mili, 'score': '80'}
        a = requests.post(urlss,data=datas,files=files,auth=auth_id)
        temp.append(a.json()['body']['imageId'])
        response = json.dumps(a.json(), indent=4)
        print(a.text,response)
        print("IMAGE UPLOAD TEST CASES IS COMPLETED")
        # print json pretty format
    return temp

#print(image_upload_data())

def upload_visitor_data():
    ur = str(url)+str( end_point_vud)
    trackId = str(with_out_mili)+'-'+str(va)
    print(trackId)
    json_string = {"tillId":till_id,"eventTime":"", "trackId":trackId,"storeId":storeId,"orgId":orgId,"deviceId":deviceId,"startTime": va,"eventType": eventType,"imageList":temp} # ['5df2196440f1dd488ebf9707','5df21f4e40f1dd488ebf993b','5df21fed40f1dd488ebf9984','5df2203140f1dd488ebf99a5','5df22069ce2d4648bbf8129d']
    print(type(json_string))
    req = requests.post(ur,data=(json_string),auth=auth_id)
    response = json.dumps(req.json(), indent=4)
    print(response)
    re = req.json()
    status =re["status"]["message"]
    assert status == "created new visitor doc","Expected Result '{}'  but Acutual Result is '{}' !!. ".format("created new visitor doc",status)
    print("document_created :",status)
    visitor_id =(re['body'])
    assert visitor_id !=int,"visitor should not be zero or blank"
    print("Visitor_Id:",visitor_id)
    # Mongo Db Query for
    # db.bodysegmentations.find({"visitor": ObjectId("5d10b5b601b00626caa54840")})
    # db.badgeidentifications.find({"visitor" : ObjectId("5d7215250569d7424be32944")})
    # db.demographies.find({"visitor" : ObjectId("5d10b5b601b00626caa54840")})
    # db.dwelltimes.find({"visitor" : ObjectId("5d10b5b601b00626caa54840")})
    # db.customerrecognitions.find({"visitor" : ObjectId("5d10b5b601b00626caa54840")})
    # db.fashions.find({"visitor" : ObjectId("5d10b5b601b00626caa54840")})
    # db.inferences.find({"visitor" : ObjectId("5d10b5b601b00626caa54840")})


print("Upload Vissitor Data",upload_visitor_data())
'''
def post_method():
    try:
        res=requests.post(BASE_URL, data=device_platform_verifcation_body,  auth=auth)
        response_code = res.status_code
        response_data = res.content
        response_header = res.headers
        response_text = res.text
        response_url= res.url
        response = json.dumps(res.json(), indent=4)
        assert response_code == 200, "Actual Status Code is '{}'  but expected is '{}' !!!!!!!!!!!!!!!!!!!!!!!!. ".format(response_code,400)
        return response
        # return [response_code,response_url,response_data,response_text,response_header]
        except Exception as error:
        print(error)
'''

#print(post_method())


def add_device():
    try:
        Url = url + end_point_add_device
        c= {"tillname":"ce"}
        res=requests.post(Url, body='/'+str(add_device_id1)+'/'+add_device_id2,json=c,auth=auth_id)
        response_code = res.status_code
        print(json.dumps(res.json(), indent=4))
        assert response_code == 200, "Actual Status Code is '{}'  but expected is '{}' !!!!!!!!!!!!!!!!!!!!!!!!. ".format(response_code,400)
        return response_code
        # return [response_code,response_url,response_data,response_text,response_header]
    except Exception as error:
        print(error)

print("Add Device:",add_device())

def detach_device_using_deviceId():
    try:
        Url =url+end_point_detach
        res =requests.delete(Url,auth=auth_id)
        response_code = res.status_code
        print(response_code,json.dumps(res.json(), indent=4))

    except Exception as error:
        print(error)

print("Detach device using device Id",detach_device_using_deviceId())

def detach_by_tillid_devcie_type():
    try:
        Url = url+detach_by_tillid_endpoint

        res =requests.delete(url=Url,data=data_detach_by_tillid,auth=auth_id)
        response_code = res.status_code
        print(response_code,json.dumps(res.json(), indent=4))
        print(res.url)

    except Exception as error:
        print(error)

print("Detach by Till Id",detach_by_tillid_devcie_type())

def create_aws_certificate():

    try:           #'https://nightly.capillary.in/instoreai/storesense/storesense-hub/device/iot/50015469/certificate?deviceType=CE'
        urlss = url + str(aws_endpoint)
        res = requests.get(urlss,data=None,json=None,auth=auth_id)
        response_code = res.status_code
        print(json.dumps(res.json(), indent=4))
        #print()
        assert response_code == 200, "Actual Status Code is '{}'  but expected is '{}' !!!!!!!!!!!!!!!!!!!!!!!!. ".format(
            response_code, 400)
        # return response
        # return [response_code,response_url,response_data,response_text,response_header]
    except Exception as error:
        print(error)

print("Create AWS_Certificate",create_aws_certificate())

def set_config():
    urlss = url + endpoint_set_config

    #r = requests.get("https://nightly.capillary.in/instoreai/storesense/storesense-hub/mqtt/50015469/config",json=set_config_par,auth=auth_id)
    res =requests.get(urlss+scid1+'/'+scid2+'?'+se_cfig_parm+'&'+stro+'&'+org+'&'+ device_type,auth=auth_id)
    re = res.text
    print(json.dumps(res.json(), indent=4))
    return(re)

print("set_config:",set_config())

def con():
    urlss = url
    res = requests.get(urlss, json=set_config_par, auth=auth_id)
    # re = requests.get()
    response_code = res.status_code
    print(json.dumps(res.json(), indent=4))
    return (response_code)


def send_email():
    # Python code to illustrate Sending mail with attachments
    # from your Gmail account
    # libraries to be imported
    #amitsaxena9225@gmail.com

    fromaddr = "amitsaxena9225@gmail.com"
    toaddr = 'miyyyyyyy.vijay@capillarytech.com'
    # instance of MIMEMultipart
    msg = MIMEMultipart()
    # storing the senders email address
    msg['From'] = fromaddr
    # storing the receivers email address
    msg['To'] = toaddr
    # storing the subject
    msg['Subject'] = "SSHUB Automation Sanity Passed !!!!"
    # string to store the body of the mail
    body = '''<h1>SSHHUB API Completed</h1>
                <p>Getting 502 Bad gateway erorr</p>
                <p><b>Below is the list of SSHUB API's</b></p>
                <p>1.device platform verifcation</p>
                <p>2.add device</p>
                <p>3.detach device using deviceId</p>
                <p>4.detach device by tillId and deviceType</p>
                <p>5.discard device</p>
                <p>6.IMAGES</p>
                <p>7.upload_visitor</p>
                <p>8.create aws certificate</p>'''

    # attach the body with the msg instance
    msg.attach(MIMEText(body, 'html'))
    # open the file to be sent
    filename = "/home/amit.saxena@capdomain.com/PycharmProjects/CAPILLARY_REST_API_TESTING/venv/bin/REST_API_TESTING/TESTS/SSHUB/TestSuite_SsHub_API/report.html"
    attachment = open(filename, "rb")
    # instance of MIMEBase and named as p
    p = MIMEBase('application', 'octet-stream')
    # To change the payload into encoded form
    p.set_payload((attachment).read())
    # encode into base64
    encoders.encode_base64(p)
    p.add_header('Content-Disposition', "attachment; filename= %s" % filename)
    # attach the instance 'p' to instance 'msg'
    msg.attach(p)
    # creates SMTP session
    s = smtplib.SMTP('smtp.gmail.com', 587)
    # start TLS for security
    s.starttls()
    # Authentication
    s.login(fromaddr, "capillary@123")
    # Converts the Multipart msg into a string
    text = msg.as_string()
    # sending the mail
    s.sendmail(fromaddr, toaddr, text)
    # terminating the session
    s.quit()


print(send_email())
