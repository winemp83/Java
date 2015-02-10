/*
 * Copyright (C) 2015 Sascha
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ressource;

/**
 *
 * @author Sascha
 */
public class ress implements ressources{
    private String Name;
    private double MaxValue;
    private double CurValue;
    private double Production;
    private double CurProduction;
    
    @Override
    public String getCurrent(String what) {
        switch(what){
            case "name":
                return this.Name;
            case "maxV":
                return ""+this.MaxValue;
            case "curV":
                return ""+this.CurValue;
            case "proP":
                return ""+this.Production;
            case "curP":
                return ""+this.CurProduction;
            default:
                throw new UnsupportedOperationException("Please choise a Valid Chase");
        }
    }

    @Override
    public void add(String what, double value) {
        switch(what){
            case "name":
                throw new UnsupportedOperationException("What do you want to add @Name?");
            case "maxV":
                this.MaxValue += value;
                break;
            case "curV":
                this.CurValue += value;
                break;
            case "proP":
                this.Production += value;
                break;
            case "curP":
                this.CurProduction = value;
                break;
            default:
                throw new UnsupportedOperationException("Please choise a Valid Chase");
        }
    }

    @Override
    public void rem(String what, double value) {
        switch(what){
            case "name":
                throw new UnsupportedOperationException("What do you want to remove @Name?");
            case "maxV":
                this.MaxValue -= value;
                break;
            case "curV":
                this.CurValue -= value;
                break;
            case "proP":
                this.Production -= value;
                break;
            case "curP":
                this.CurProduction = value;
                break;
            default:
                throw new UnsupportedOperationException("Please choise a Valid Chase");
        }
    }

    @Override
    public void set(String what, String value) {
        switch(what){
            case "name":
                this.Name = value;
            case "maxV":
                this.MaxValue = Double.parseDouble(value);
                break;
            case "curV":
                this.CurValue = Double.parseDouble(value);
                break;
            case "proP":
                this.Production = Double.parseDouble(value);
                break;
            case "curP":
                this.CurProduction = Double.parseDouble(value);
                break;
            default:
                throw new UnsupportedOperationException("Please choise a Valid Chase");
        }
    }
    
    @Override
    public String toString(){
        return  "Name: "+this.Name+"\n"+
                "MaxValue: "+this.MaxValue+"\n"+
                "CurrentValue: "+this.CurValue+"\n"+
                "Production: "+this.Production+"\n"+
                "CurrentProduction: "+this.CurProduction+"%";
    }

}
