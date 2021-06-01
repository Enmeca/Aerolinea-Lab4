package com.cabegaira.aerolinea


class Routes {
    
    var Name:String = " "

    var Origin:String = " "
    var Destiny:String = " "

    var ExitDate:String = " "
    var Duration:String = " "

    internal constructor(Name: String, Origin: String, Destiny: String, ExitDate: String, Duration: String) {
        this.Name = Name
        this.Origin = Origin
        this.Destiny = Destiny
        this.ExitDate = ExitDate
        this.Duration = Duration
    }

}