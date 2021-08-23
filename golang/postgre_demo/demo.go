package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
)
// 需要"github.com/lib/pq"但使用_来表示只初始化不使用
var db *sql.DB

func connectDB() {
	var err error
	db, err = sql.Open("postgres", "postgres://gaussdb:Secretpassword@123@39.107.98.10:15432/newdb?sslmode=disable")
	if err != nil {
		fmt.Println(err)
	}
}
func queryDB() {
	rows, err := db.Query("SELECT * FROM company2")
	if err != nil {
		fmt.Println(err)
	}

	println("-----------")
	for rows.Next() {
		var id string
		var name string
		var age string
		var address string
		var salary string
		err = rows.Scan(&id, &name, &age, &address, &salary)
		if err != nil {
			fmt.Println(err)
		}
		fmt.Println("id = ", id, "\nname = ", name, "\nage = ", age, "\naddress = ", address, "\nsalary", salary, "\n-----------")
	}
}
func main() {
	connectDB()
	queryDB()
}
