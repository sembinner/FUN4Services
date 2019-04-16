package service

import (
	"../domain"
	"io/ioutil"
	"log"
	"time"
)

type ConfigService struct {
	Config *domain.Config

	Location string
}

//Watch reloads the config every d seconds
func (s *ConfigService) Watch(d time.Duration) {
	for {
		err := s.Reload()
		if err != nil {
			log.Print(err)
		}

		time.Sleep(d)
	}
}

// Reload reads the config and applies changes
func (s *ConfigService) Reload() error {
	data, err := ioutil.ReadFile(s.Location)
	if err != nil {
		return err
	}

	err = s.Config.SetFromBytes(data)
	if err != nil {
		return err
	}

	return nil
}
