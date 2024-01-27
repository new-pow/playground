package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func main() {
	// end point set
	endpoints := []string{"https://github.com/new-pow"}

	for _, endpoint := range endpoints {
		content, err := scrape(endpoint)
		if err != nil {
			// handle error
			fmt.Println("Error:", err)
		} else {
			fmt.Println(content)
		}
	}
}

func scrape(endpoint string) (content string, err error) {
	resp, err := http.Get(endpoint)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("Failed to fetch. Status code: %d", resp.StatusCode)
	}

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	return string(body), nil
}
