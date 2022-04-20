'''
Author: Guowen Liu
Date: 2022-04-15 15:33:25
LastEditors: Guowen Liu
LastEditTime: 2022-04-20 04:05:18
Copyright (c) 2022 by Guowen Liu, All Rights Reserved.
'''
import datetime
import random
import locust
from locust import HttpUser, task, User, between, SequentialTaskSet, TaskSet
from locust.env import Environment
import time;
import new

class user(SequentialTaskSet):
    _BASE_URL = 'http://52.190.2.8'
    _LOGIN_URL = _BASE_URL+':8080/user/login'
    _SERVICE_POST_LOGIN = {"email": "liu@wada.com","password": "liu"}
    _ADO_ID = '625d977125ae313cadf7fae9'
    _GET_AVAILABILITY_ATHLETE = _BASE_URL+':8080/availability/athlete/'
    _POST_ADD_AVAILABILITY = _BASE_URL+':8080/availability/'
    _PATCH_UPDATE_AVAILABILITY = _BASE_URL + ':8080/availability/'
    _DELETE_AVAILABILITY = _BASE_URL+':8080/availability/'
    _POST_ALL_APPOINTMENTS = _BASE_URL+':8080/ado/getAllAppointments'
    _HEADERS = {'content-type': 'application/json'}
    _GET_LOCATIONS = _BASE_URL + ':8080/location'
    _availability_timestamp = round(time.time() * 1000)
    _delete_availability_id = ''
    _TOKEN = ''
    _ID = ''
    _current_headers = ''

    def on_start(self):
        self.athletes_login()
        
        all_athletes = new._ATHLETES
        self.athlete = new.get_random_from_list(all_athletes)
        
        self.locations = new._LOCATIONS
        self._current_headers = new._HEADERS
        return super().on_start()

    def get_new_availability(self):
        end_time=datetime.datetime.now() + datetime.timedelta(minutes=+ random.randint(1,10))
        start_time=datetime.datetime.now()
        a1=tuple(start_time.timetuple()[0:9])
        a2=tuple(end_time.timetuple()[0:9])
        start=time.mktime(a1)
        end=time.mktime(a2)
        t=random.randint(start,end)
        tomorrowtimestamp =t*1000000+random.randint(1,999999)
        body = {
            "startTimeStamp": tomorrowtimestamp,
            "location": new.get_random_from_list(self.locations)
        }
        self._availability_timestamp = tomorrowtimestamp
        return body

    def athletes_login(self):
        resp = self.client.post(self._LOGIN_URL,json=self._SERVICE_POST_LOGIN,headers=self._HEADERS)
        assert resp.status_code == 200
        json_response_dict = resp.json()
        self._TOKEN = "Bearer " + json_response_dict['token']
        self._ID = json_response_dict['id']
        self._current_headers = {'content-type': 'application/json',"Authorization": self._TOKEN}

        print(f'login : Athletes login success! Token:{ self._TOKEN} ID:{self._ID} \r\n')
        return

    @locust.task
    def get_locations(self):
        resp = self.client.get(self._GET_LOCATIONS,headers= self._current_headers)
        print("get locations : success !\r\n")

        return

    
    @locust.task
    def get_availability_athletes(self):
        resp = self.client.get(self._GET_AVAILABILITY_ATHLETE+self._ID,headers={"Authorization": self._TOKEN})
        assert resp.status_code == 200
        print("get athlete availability : success !\r\n")
        return


    @locust.task
    def post_appointments(self):
        body = {"adoId":"625e182e55a21d77983dc5fb"}
        resp = self.client.post(self._POST_ALL_APPOINTMENTS,headers= self._current_headers,json=body)
        assert resp.status_code == 200
        print("get all appointments : success !\r\n")
        return

    @locust.task
    def get_athletes_for_ado(self):
        body = {"adoId":"625e182e55a21d77983dc5fb"}
        resp = self.client.post("http://52.190.2.8:8080/ado/getAthletes",headers= self._current_headers,json=body)
        assert resp.status_code == 200
        print("get all appointments : success !\r\n")
        return

class UserInstance(HttpUser):
    wait_time = between(1,1)
    tasks = [user]