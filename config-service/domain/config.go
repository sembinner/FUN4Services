package domain

import (
	"fmt"
	"github.com/alecthomas/gometalinter/_linters/src/gopkg.in/yaml.v2"
	"sync"
)

// Config is an abstraction around the map that holds the config values
type Config struct {
	config map[string]interface{}
	lock sync.RWMutex
}

// Get returns the config for a particular service
func (c *Config) Get(serviceName string) (map[string]interface{}, error)  {
	c.lock.RLock()
	defer c.lock.RUnlock()

	a, ok := c.config["base"].(map[string]interface{})
	if !ok {
		return nil, fmt.Errorf("base config is not a map")
	}

	// If no config is defined for the service
	if _, ok = c.config[serviceName]; !ok {
		// Return the base config
		return a, nil
	}

	b, ok := c.config[serviceName].(map[string]interface{})
	if !ok {
		return nil, fmt.Errorf("service %q config is not a map", serviceName)
	}

	// Merge the maps with the service config taking precedence
	config := make(map[string]interface{})
	for k, v := range a {
		config[k] = v
	}

	for k, v := range b {
		config[k] = v
	}

	return config, nil
}

// SetFromBytes sets the internal config based on a byte array of YAML
func (c *Config) SetFromBytes(data []byte) error {
	var rawConfig interface{}
	if err := yaml.Unmarshal(data, &rawConfig); err != nil {
		return err
	}

	// Check if it is a map
	untypedConfig, ok := rawConfig.(map[interface{}]interface{})
	if !ok {
		return fmt.Errorf("config is not a map")
	}

	// Converting the bytes to strings, recursion with key value pairs
	config, err := convertKeysToStrings(untypedConfig)

	if err != nil {
		return err
	}

	c.lock.Lock()
	defer c.lock.Unlock()

	c.config = config

	return nil
}

//Converting keys to string, when not a string return an error
//Every key in the yaml file should be a string
func convertKeysToStrings(original map[interface{}]interface{}) (map[string]interface{}, error){
	nw := make(map[string]interface{})

	for k, v := range original {

		//Check if its a string, other wise not 'ok'
		str, ok := k.(string)

		// If not a string, return an error
		if !ok {
			return nil, fmt.Errorf("config key is not a string")
		}

		// Recursion
		if vMap, ok := v.(map[interface{}]interface{}); ok {

			//Converting to string (with possible recursion)
			var err error
			v, err = convertKeysToStrings(vMap)
			if err != nil {
				return nil, err
			}
		}

		nw[str] = v
	}

	return nw, nil
}