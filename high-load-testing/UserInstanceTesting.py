'''
Author: Guowen Liu
Date: 2022-04-15 15:33:25
LastEditors: Guowen Liu
LastEditTime: 2022-04-17 17:41:45
Copyright (c) 2022 by Guowen Liu, All Rights Reserved.
'''
import random
import uuid
import locust
from locust import HttpUser, task, User, between, SequentialTaskSet, TaskSet
from locust.env import Environment
import json

class UserInstance(HttpUser):
    wait_time = between(1, 2)
    _REGISTRATION_URL = '/user/register'
    _LOCATIONS_URL = ':8006/location'
    _LOGIN_URL = ':8006/user/login'
    _AVAILABILITY_GET_ALL = ':8005/availability'

    _ADO_GET_ALL_ATHLETES = ':8007/ado/getAthletes'
    _ADO_GET_APPOINTMENTS = ':8007/ado/getAllAppointments'
    _SERVICE_POST_LOGIN = {"email": "athlete@wada.com","password": "athlete"}
    _ADO_ID = {"adoId": "6256086d43c3bf60eb4fc63e"}
    _HEADERS = {'content-type': 'application/json'}

    @locust.task
    def get_locations(self):
        resp = self.client.get(self._LOCATIONS_URL)
        assert resp.status_code == 200
        print("USER_SERVICE : Get locations success !\r\n")
        return

    @locust.task
    def athletes_login(self):
        resp = self.client.post(self._LOGIN_URL,json=self._SERVICE_POST_LOGIN,headers=self._HEADERS)
        assert resp.status_code == 200
        print("USER_SERVICE : Athletes login success !\r\n")
        return


    @locust.task
    def get_all_athletes_ado(self):
        resp = self.client.post(self._ADO_GET_ALL_ATHLETES, json=self._ADO_ID,headers=self._HEADERS)
        assert resp.status_code == 200
        print("ADO_SERVICE : Get all athletes for ADO success !\r\n")
        return
    
    @locust.task
    def get_appointments(self):
        resp = self.client.post(self._ADO_GET_APPOINTMENTS, json=self._ADO_ID,headers=self._HEADERS)
        assert resp.status_code == 200
        print("ADO_SERVICE : Get appointments success !\r\n")
        return

    @locust.task
    def get_all_availability(self):
        resp = self.client.get(self._AVAILABILITY_GET_ALL)
        assert resp.status_code == 200
        print("AVAILABILITY : Get all availability success !\r\n")
        return
