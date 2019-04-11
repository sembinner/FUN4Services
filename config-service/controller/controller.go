package controller

import (
	"../../config-service/domain"
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"net/http"
)

type Controller struct {
	Config *domain.Config
}

// ReadConfig writes the config for the given service to the ResponseWriter
func (c *Controller) ReadConfig(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")

	vars := mux.Vars(r)
	serviceName, ok := vars["serviceName"]
	if !ok {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Fprintf(w, "error")
	}

	config, err := c.Config.Get(serviceName)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintf(w, "error")
	}

	rsp, err := json.Marshal(&config)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintf(w, "error")
	}

	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, string(rsp))
}