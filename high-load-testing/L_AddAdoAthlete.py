'''
Author: Guowen Liu
Date: 2022-04-15 15:33:25
LastEditors: Guowen Liu
LastEditTime: 2022-04-17 21:58:57
Copyright (c) 2022 by Guowen Liu, All Rights Reserved.
'''
import datetime
import random
import locust
from locust import HttpUser, task, User, between, SequentialTaskSet, TaskSet
from locust.env import Environment
import time;
import new
from faker import Faker
import requests
import json

class user(SequentialTaskSet):
    # _BASE_URL = 'http://52.190.2.8'
    # _LOGIN_URL = _BASE_URL+':8080/user/login'
    # _SERVICE_POST_LOGIN = {"email": "athlete_liu@wada.com","password": "athlete_liu"}
    # _ADO_ID = '625d977125ae313cadf7fae9'
    # _GET_AVAILABILITY_ATHLETE = _BASE_URL+':8080/availability/athlete/'
    # _POST_ADD_AVAILABILITY = _BASE_URL+':8080/availability/'
    # _PATCH_UPDATE_AVAILABILITY = _BASE_URL + ':8080/availability/'
    # _DELETE_AVAILABILITY = _BASE_URL+':8080/availability/'
    # _POST_ALL_APPOINTMENTS = _BASE_URL+':8080/ado/getAllAppointments'
    # _HEADERS = {'content-type': 'application/json'}
    # _GET_LOCATIONS = _BASE_URL + ':8080/location'
    # _availability_timestamp = round(time.time() * 1000)
    # _delete_availability_id = ''
    # _TOKEN = ''
    # _ID = ''
    # _current_headers = ''
    
    xBASE = "http://52.190.2.8:8080"
    xGET_LOCATIONS = xBASE + "/location"
    xREGISTER_USER = xBASE + "/user/register"

    def on_start(self):
        # self.athletes_login()
        self.locations = new._LOCATIONS
        self._current_headers = new._HEADERS
        
        self.fake = Faker()
        return super().on_start()

    # def get_new_availability(self):
    #     end_time=datetime.datetime.now() + datetime.timedelta(minutes=+ random.randint(1,10))
    #     start_time=datetime.datetime.now()
    #     a1=tuple(start_time.timetuple()[0:9])
    #     a2=tuple(end_time.timetuple()[0:9])
    #     start=time.mktime(a1)
    #     end=time.mktime(a2)
    #     t=random.randint(start,end)
    #     tomorrowtimestamp =t*1000000
    #     body = {
    #         "startTimeStamp": tomorrowtimestamp,
    #         "location": new.get_random_from_list(self.locations)
    #     }
    #     self._availability_timestamp = tomorrowtimestamp
    #     return body

    # def athletes_login(self):
    #     resp = self.client.post(self._LOGIN_URL,json=self._SERVICE_POST_LOGIN,headers=self._HEADERS)
    #     assert resp.status_code == 200
    #     json_response_dict = resp.json()
    #     self._TOKEN = "Bearer " + json_response_dict['token']
    #     self._ID = json_response_dict['id']
    #     self._current_headers = {'content-type': 'application/json',"Authorization": self._TOKEN}

    #     print(f'login : Athletes login success! Token:{ self._TOKEN} ID:{self._ID} \r\n')
    #     return

    def register_athlete(self, user_object):
        user_object["isAthlete"] = True
        headers = {
            'Content-Type': 'application/json'
        }
        payload = json.dumps(user_object)
        response = self.client.post(self.xREGISTER_USER, headers=headers, data=payload)
        return response
    
    def register_ado(self, user_object):
        user_object["isAthlete"] = False
        headers = {
            'Content-Type': 'application/json'
        }
        payload = json.dumps(user_object)
        response = self.client.post(self.xREGISTER_USER, headers=headers, data=payload)
        return response

    @locust.task
    def add_athlete(self):
        user_obj = {}
        fake_name = self.fake.name()
        user_obj['name'] = fake_name
        user_obj['password'] = fake_name
        user_obj['email'] = f'{fake_name.replace(" ", "").lower()}{random.randint(1, 1500)}@wada.com'
        user_obj['location'] = new.get_random_from_list(self.locations)
    
        response = self.register_athlete(user_obj)
        if (response.status_code == 200):
            pass
        else:
            print(f'{response.status_code}: {response.text}')
            
    @locust.task
    def add_ado(self):
        user_obj = {}
        fake_name = self.fake.name()
        user_obj['name'] = fake_name
        user_obj['password'] = fake_name
        user_obj['email'] = f'{fake_name.replace(" ", "").lower()}{random.randint(1, 1500)}@wada.com'
        user_obj['location'] = new.get_random_from_list(self.locations)
    
        response = self.register_athlete(user_obj)
        if (response.status_code == 200):
            pass
        else:
            print(f'{response.status_code}: {response.text}')

class UserInstance(HttpUser):
    wait_time = between(1,1)
    tasks = [user]