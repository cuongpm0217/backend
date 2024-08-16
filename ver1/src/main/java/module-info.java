/**
 *
 */
/**
 *
 */
open module ver1 {
	exports com.hongha.ver1.securties.jwt;
	exports com.hongha.ver1.configuration;
	exports com.hongha.ver1.repositories;
	exports com.hongha.ver1.services.impl;
	exports com.hongha.ver1.services;
	exports com.hongha.ver1.controllers.request;
	exports com.hongha.ver1.dtos;
	exports com.hongha.ver1.entities;
	exports com.hongha.ver1.utils;
	exports com.hongha.ver1.redisService;
	exports com.hongha.ver1.securties.jwt.payload.response;
	exports com.hongha.ver1.entities.listeners;
	exports com.hongha.ver1.controllers;
	exports com.hongha.ver1.securties;
	exports com.hongha.ver1.securties.jwt.payload.request;
	exports com.hongha.ver1.redisService.impl;
	exports com.hongha.ver1.entities.enums;
	exports com.hongha.ver1;
	exports com.hongha.ver1.controllers.response;

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires jakarta.persistence;
	requires java.xml;
	requires jjwt.api;
	requires lombok;
	requires modelmapper;
	requires org.hibernate.orm.core;
	requires org.slf4j;
	requires poi;
	requires poi.ooxml;
	requires spring.aop;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.data.jpa;
	requires spring.data.redis;
	requires spring.jcl;
	requires spring.security.config;
	requires spring.security.core;
	requires spring.security.crypto;
	requires spring.security.web;
	requires spring.tx;
	requires spring.web;
	requires tomcat.embed.core;
}