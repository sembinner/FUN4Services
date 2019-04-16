package main

import (
	"../config-service/controller"
	"../config-service/domain"
	"../config-service/service"
	"github.com/jakewright/muxinator"
	"log"
	"time"
)

func main() {
	config := domain.Config{}

	configService := service.ConfigService{
		Config: &config,
		Location: "config.yml",
	}

	// Checks every 60 seconds for changes in the config.yml
	go configService.Watch(time.Second * 3)

	c := controller.Controller{
		Config: &config,
	}

	router := muxinator.NewRouter()
	router.Get("/read/{serviceName}", c.ReadConfig)
	log.Fatal(router.ListenAndServe(":9005"))
}
