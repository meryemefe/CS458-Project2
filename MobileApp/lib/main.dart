import 'dart:async';

import 'package:flutter/material.dart';
import 'package:date_field/date_field.dart';
import 'package:flutter/rendering.dart';

void main() {
  runApp(SurveyApp());
}

class SurveyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold (
        appBar: AppBar(
          title: new Center(
            child: Text("Covid-19 Survey", textAlign: TextAlign.center)
          ),
          backgroundColor: Colors.red,
        ),
        body: SingleChildScrollView(
          child: SurveyForm()
        ),
      ),
    );
  }
}

class SurveyForm extends StatefulWidget {
  GlobalKey<_SurveyFormState> key = GlobalKey();
  @override
  _SurveyFormState createState() => _SurveyFormState(key);


}

class _SurveyFormState extends State<SurveyForm> {

  final _surveyFormKey = GlobalKey<FormState>();
  String sendDataLine = "Your data is sent";
  bool dataSent = false;
  GlobalKey<_SurveyFormState> key;
  _SurveyFormState(GlobalKey<_SurveyFormState> key)
  {
    this.key = key;
  }

  GlobalKey<_CustomDropDownState> _genderKey = GlobalKey();
  GlobalKey<_CustomDropDownState> _vaccineKey = GlobalKey();

  List<bool> isValidatedArr = [false, false, false, false, false, false, true];

  bool isVisible = false;

  void changeValidateArr(int index, bool val) {
    print(index);
    print(val);
    setState(() {
      isValidatedArr[index] = val;
      if (isValidatedArr[0] && isValidatedArr[1] && isValidatedArr[2] && isValidatedArr[3] && isValidatedArr[4] && isValidatedArr[5] && isValidatedArr[6]) {
        isVisible = true;
      } else {
        isVisible = false;
      }
    });
  }

  void resetForm(List<CustomTextFormField> customForms){
    _surveyFormKey.currentState.reset();
    _genderKey.currentState.reset();
    _vaccineKey.currentState.reset();
    for(CustomTextFormField field in customForms){
      field.reset();
    }
    for(int i = 0; i < isValidatedArr.length-1; i++) {
      changeValidateArr(i, false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final halfScreenWidth = MediaQuery.of(context).size.width / 2.0;

    List<CustomTextFormField> customForms = [];

    // NAME

    CustomTextFormField nameWidget = new CustomTextFormField(
            index: 0,
            hintText: "Name",
            validator: (String value) {
              if (value.isEmpty) {
                return 'Name cannot be empty.';
              } else if (value.length == 1) {
                return "Name cannot be 1\ncharacter.";
              } else if (RegExp(r'[!@#<>?":_`~;[\]\\|=+)(*&^%0-9-]').hasMatch(value)) {
                return "Name cannot contain\nnumerical character.";
              } else {
                return null;
              }
            },
            form: key,
        );

    // SURNAME

    CustomTextFormField surnameWidget  = new CustomTextFormField(
      index: 1,
      hintText: "Surname",
      validator: (String value) {
        if (value.isEmpty) {
          return 'Surname cannot be empty.';
        } else if (value.length == 1) {
          return "Surname cannot be 1\ncharacter.";
        } else if (RegExp(r'[!@#<>?":_`~;[\]\\|=+)(*&^%0-9-]').hasMatch(value)) {
          return "Surname cannot contain\nnumerical character.";
        } else {
          return null;
        }
      },
      form: key,
    );

    // DATE

    FormField dateWidget = new DateTimeFormField(
      decoration: const InputDecoration(
        hintStyle: TextStyle(color: Colors.black45),
        errorStyle: TextStyle(color: Colors.redAccent),
        border: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(12.0))
        ),
        suffixIcon: Icon(Icons.event_note),
        labelText: "Birth Date",
        contentPadding: EdgeInsets.all(12),
        filled: true,
        fillColor: Colors.white,
      ),
      validator: (DateTime value) {
        if (value == null) {
          return "Birth date cannot be empty.";
        } else if (value.isAfter(DateTime.now())) {
          return "Birth date cannot be in future.";
        }
        else {
          return null;
        }
      },
      mode: DateTimeFieldPickerMode.date,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      onDateSelected: (DateTime value) {
        if (value == null) {
        changeValidateArr(2, false);
        } else if (value.isAfter(DateTime.now())) {
        changeValidateArr(2, false);
        }
        else {
        changeValidateArr(2, true);
        }
      },
    );

    // CITY

    CustomTextFormField cityWidget = new CustomTextFormField(
      index: 3,
      hintText: "City",
      validator: (String value) {
        if (value.isEmpty) {
          return 'Please select a city';
        } else {
          return null;
        }
      },
      form: key,
    );

    // GENDER

    DropDown genderDD = new DropDown(
        defaultValue:'Please select a gender',
        validator: (String value) {
         if (value == 'Please select a gender') {
           return 'Please select a gender';
         } else {
           return null;
         }
        },
        items: ['Please select a gender','Male', 'Female']);

    // VACCINE TYPE

    DropDown vaccineDD = new DropDown(
        defaultValue:'Please select a vaccine',
        validator: (String value) {
          if (value == 'Please select a vaccine') {
            return 'Please select a vaccine';
          } else {
            return null;
          }
        },
        items: ['Please select a vaccine','China-Coronovac', 'Germany-Pfizer', 'USA-Moderna', 'Turkey-Tarhanovac']);

    GlobalKey _dataSentKey = GlobalKey();

    customForms.add(cityWidget);
    customForms.add(nameWidget);
    customForms.add(surnameWidget);

    return Form(
      key: _surveyFormKey,
      child: Column(
        children: <Widget>[
          Row (
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                alignment: Alignment.topLeft,
                width: halfScreenWidth,
                child: nameWidget,
              ),
              Container(
                alignment: Alignment.topRight,
                width: halfScreenWidth,
                child: surnameWidget,
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: Container(
              child: dateWidget,
            ),
          ),

          Container(
              child: cityWidget,
          ),

          CustomDropDown(
            index: 4,
            key: _genderKey,
            content: genderDD,
            form: key,
          ),

          CustomDropDown(
            index: 5,
            key: _vaccineKey,
            content: vaccineDD,
            form: key,
          ),
//          CustomTextFormField(
//            hintText: "Gender",
//            validator: null,
//          ),
//          CustomTextFormField(
//            hintText: "Vaccine type they applied",
//          ),

          CustomTextFormField(
            hintText: "Side effect after vaccination",
          ),
          Row (
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                alignment: Alignment.center,
                width: MediaQuery.of(context).size.width / 2,
                child: Container(
                      child: MaterialButton(
                          shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(17.0),
                        ),
                        color: Colors.red,
                        textColor: Colors.white,
                        child: Text(
                          "Clear",
                          style: TextStyle(
                            color: Colors.white,
                          ),
                        ),
                        onPressed: () async {
                            resetForm(customForms);
                        },
                      ),
                  ),
                ),
              Container(
                alignment: Alignment.center,
                width: MediaQuery.of(context).size.width / 2,
                child:  Visibility(
                  visible: isVisible,
                  child: MaterialButton(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(17.0),
                    ),
                    color: Colors.green,
                    textColor: Colors.white,
                    child: Text(
                      "Send",
                      style: TextStyle(
                        color: Colors.white,
                      ),
                    ),
                    onPressed: () async {

                      setState(() {
                        dataSent = true;
                        if (_vaccineKey.currentState.chosenValue == 'Germany-Pfizer'){
                          sendDataLine = "Ihre Daten werden gesendet!";
                        }else if (_vaccineKey.currentState.chosenValue == 'China-Coronovac'){
                          sendDataLine = "Nín de shùjù yǐ fāsòng!";
                        }else if (_vaccineKey.currentState.chosenValue == 'Turkey-Tarhanovac'){
                          sendDataLine = "Bilgileriniz gönderildi!";
                        } else {
                          sendDataLine = "Your data is sent!";
                        }
                        resetForm(customForms);
                      });
                    },
                  ),
                ),
              ),
            ],
          ),



          Offstage (
            key: _dataSentKey,
            offstage: !dataSent,
            child: Container(
                padding: EdgeInsets.all(8.0),
                child: Text(sendDataLine,
                  style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),) ),
          ),
        ],
      )
    );
  }
}



class CustomTextFormField extends StatelessWidget {
  final String hintText;
  final Function validator;
  var content = TextEditingController();
  GlobalKey<_SurveyFormState> form;
  int index;
  Key key;
  CustomTextFormField({
    this.index,
    this.key,
    this.hintText,
    this.validator,
    this.form
});
  void reset(){
    content.text = "";
  }
  @override
  Widget build(BuildContext context) {
    String _text;
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: TextFormField(
        onSaved: (str) => _text = str,
        onChanged: (String value) {
          form.currentState.changeValidateArr(index, this.validator(value) == null);
        },
        autovalidateMode: AutovalidateMode.onUserInteraction,
        validator: this.validator,
        decoration: InputDecoration(
          hintText: hintText,
          contentPadding: EdgeInsets.all(12),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(15.0),
          ),
          filled: true,
          fillColor: Colors.white,
        ),
      ),
    );
  }
}

class DropDown{
  String defaultValue;
  List<String> items;
  final Function validator;
  DropDown({this.defaultValue, this.validator, this.items});
}

class CustomDropDown extends StatefulWidget{
  int index;
  DropDown content;
  GlobalKey<_SurveyFormState> form;
  Key key;
  CustomDropDown({this.index, this.key, this.content, this.form});
  @override
  _CustomDropDownState createState() => _CustomDropDownState(index, content, form);
}

class _CustomDropDownState extends State<CustomDropDown> {
  int index;
  String defaultValue;
  String chosenValue;
  Function validator;
  List<String> items;
  GlobalKey<_SurveyFormState> form;

  _CustomDropDownState(int index, DropDown _content, GlobalKey<_SurveyFormState> form){
    defaultValue = _content.defaultValue;
    chosenValue = _content.defaultValue;
    validator = _content.validator;
    items = _content.items;
    this.form = form;
    this.index = index;
  }

  @override
  Widget build(BuildContext context){
    return Padding(
        padding: EdgeInsets.all(8.0),
        child: Container(
          alignment: Alignment.center,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(12.0),
            color: Colors.white,
          ),
          child: DropdownButtonFormField<String>(
            isExpanded: true,
            focusColor:Colors.white,
            value: chosenValue,
            style: TextStyle(color: Colors.white),
            iconEnabledColor:Colors.black,
            decoration: InputDecoration(
              contentPadding: EdgeInsets.all(12),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(15.0),
              ),
              filled: true,
              fillColor: Colors.white,
            ),
            items: items.map<DropdownMenuItem<String>>((String value) {
              return DropdownMenuItem<String>(
                value: value,
                child: Text(
                  value,
                  style:TextStyle(color:Colors.black),),
              );
            }).toList(),
            autovalidateMode: AutovalidateMode.onUserInteraction,
            validator: validator,
            onChanged: (String value) {
              form.currentState.changeValidateArr(index, this.validator(value) == null);
              setState(() {
                chosenValue = value;
              });
            },
          ),
        ),
    );
  }

  void printValue(){
    print(chosenValue);
  }

  void reset(){
    setState(() {
      chosenValue = defaultValue;
    });
  }
}