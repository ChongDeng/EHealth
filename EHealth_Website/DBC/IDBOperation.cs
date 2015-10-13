using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;

namespace EHealth.DBC
{
    interface IDBOperation
    {
        DataRow findDoctorByName(String name); 
    }
}
