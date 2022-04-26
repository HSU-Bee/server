window.swaggerSpec={
  "openapi" : "3.0.1",
  "info" : {
    "title" : "PETRA API",
    "description" : "PETRA API description",
    "version" : "0.1.0"
  },
  "servers" : [ {
    "url" : "http://localhost:8080"
  } ],
  "tags" : [ ],
  "paths" : {
    "/login" : {
      "post" : {
        "tags" : [ "login" ],
        "summary" : "로그인 후 쿠키 발급",
        "description" : "로그인 후 쿠키를 발급받을 수 있습니다.",
        "operationId" : "LogIn and Get Cookie",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/login486549215"
              },
              "examples" : {
                "LogIn and Get Cookie" : {
                  "value" : "{\"id\":\"heather\",\"name\":\"������\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/login-1664081379"
                },
                "examples" : {
                  "LogIn and Get Cookie" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\"\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/schedules/copy-create-schedule" : {
      "post" : {
        "tags" : [ "schedules" ],
        "summary" : "기존 스케줄의 Plan -> 새 스케줄 생성 후 삽입",
        "description" : "기존 스케줄에서 Plan들을 복사하여 새 스케줄을 생성 후 삽입합니다.",
        "operationId" : "Copy Plan to New Schedule",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/schedules-copy-create-schedule-290318001"
              },
              "examples" : {
                "Copy Plan to New Schedule" : {
                  "value" : "{\r\n  \"userId\" : \"park1\",\r\n  \"planIdList\" : [ 13, 60, 61 ],\r\n  \"scheduleId\" : 1\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/schedules-copy-create-schedule1213208896"
                },
                "examples" : {
                  "Copy Plan to New Schedule" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"userId\" : \"park1\",\r\n    \"scheduleId\" : 2,\r\n    \"planList\" : [ {\r\n      \"planId\" : 124,\r\n      \"memo\" : \"plan1\",\r\n      \"order\" : 1,\r\n      \"startDate\" : \"2022-04-27\",\r\n      \"endDate\" : \"2022-04-27\",\r\n      \"attractionName\" : \"국립 청태산자연휴양림\",\r\n      \"scheduleId\" : 2\r\n    }, {\r\n      \"planId\" : 125,\r\n      \"memo\" : \"plan1\",\r\n      \"order\" : 2,\r\n      \"startDate\" : \"2022-04-27\",\r\n      \"endDate\" : \"2022-04-27\",\r\n      \"attractionName\" : \"국립 청태산자연휴양림\",\r\n      \"scheduleId\" : 2\r\n    }, {\r\n      \"planId\" : 126,\r\n      \"memo\" : \"plan1\",\r\n      \"order\" : 3,\r\n      \"startDate\" : \"2022-04-27\",\r\n      \"endDate\" : \"2022-04-27\",\r\n      \"attractionName\" : \"국립 청태산자연휴양림\",\r\n      \"scheduleId\" : 2\r\n    } ]\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/schedules/copy-paste-schedule" : {
      "post" : {
        "tags" : [ "schedules" ],
        "summary" : "기존 스케줄의 Plan -> 기존의 다른 스케줄에 삽입",
        "description" : "기존 스케줄에서 Plan들을 복사하여 기존의 다른 스케줄에 삽입합니다.",
        "operationId" : "Copy Plan to existing Schedule",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/schedules-copy-paste-schedule-597913930"
              },
              "examples" : {
                "Copy Plan to existing Schedule" : {
                  "value" : "{\r\n  \"userId\" : \"park1\",\r\n  \"planIdList\" : [ 13, 60, 61 ],\r\n  \"scheduleId\" : 1,\r\n  \"newScheduleId\" : 2\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/schedules-copy-create-schedule1213208896"
                },
                "examples" : {
                  "Copy Plan to existing Schedule" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"userId\" : \"park1\",\r\n    \"scheduleId\" : 2,\r\n    \"planList\" : [ {\r\n      \"planId\" : 124,\r\n      \"memo\" : \"plan1\",\r\n      \"order\" : 1,\r\n      \"startDate\" : \"2022-04-27\",\r\n      \"endDate\" : \"2022-04-27\",\r\n      \"attractionName\" : \"국립 청태산자연휴양림\",\r\n      \"scheduleId\" : 2\r\n    }, {\r\n      \"planId\" : 125,\r\n      \"memo\" : \"plan1\",\r\n      \"order\" : 2,\r\n      \"startDate\" : \"2022-04-27\",\r\n      \"endDate\" : \"2022-04-27\",\r\n      \"attractionName\" : \"국립 청태산자연휴양림\",\r\n      \"scheduleId\" : 2\r\n    }, {\r\n      \"planId\" : 126,\r\n      \"memo\" : \"plan1\",\r\n      \"order\" : 3,\r\n      \"startDate\" : \"2022-04-27\",\r\n      \"endDate\" : \"2022-04-27\",\r\n      \"attractionName\" : \"국립 청태산자연휴양림\",\r\n      \"scheduleId\" : 2\r\n    } ]\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/users/travel-types" : {
      "post" : {
        "tags" : [ "users" ],
        "summary" : "여행 타입 부여",
        "description" : "사용자의 응답을 기반으로 여행 타입을 부여합니다.",
        "operationId" : "Grant TravelType",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/users-travel-types2059588752"
              },
              "examples" : {
                "Grant TravelType" : {
                  "value" : "{\r\n  \"id\" : \"park1\",\r\n  \"answer\" : [ \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\" ]\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/users-travel-types-450931574"
                },
                "examples" : {
                  "Grant TravelType" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"typeName\" : \"양귀비<위로, 위안>\",\r\n    \"typeDesc\" : \"도비 이즈 프리!! 도비는 자유를 찾아 떠나요~ 양귀비의 꽃말 위로, 위안과 잘 어울리는 당신! 여행을 떠날때는 주로 위로와 위안을 찾아 떠납니다. 당신은 때로는 힘든 생기면 도피하고 싶은 경향이 있습니다. 힘든 일상으로부터 도망치듯이 여행을 떠나는 당신은 여행에서 휴식과 편안함을 가장 중요하게 여기네요. 당신에게 추천하는 여행은 주로 호캉스, 스파, 식물원 등이 있습니다~\",\r\n    \"typeImageUrl\" : \"https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg\"\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "schedules-copy-create-schedule1213208896" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "data" : {
            "type" : "object",
            "properties" : {
              "planList" : {
                "type" : "array",
                "items" : {
                  "type" : "object",
                  "properties" : {
                    "endDate" : {
                      "type" : "string",
                      "description" : "plan의 종료일"
                    },
                    "memo" : {
                      "type" : "string",
                      "description" : "plan의 메모"
                    },
                    "planId" : {
                      "type" : "number",
                      "description" : "복사 생성된 plan id"
                    },
                    "scheduleId" : {
                      "type" : "number",
                      "description" : "plan이 저장된 schedule Id"
                    },
                    "startDate" : {
                      "type" : "string",
                      "description" : "plan의 시작일"
                    },
                    "attractionName" : {
                      "type" : "string",
                      "description" : "plan 속 관광지 이름"
                    },
                    "order" : {
                      "type" : "number",
                      "description" : "schedule 내부에서 plan의 순서"
                    }
                  }
                }
              },
              "userId" : {
                "type" : "string",
                "description" : "사용자 ID"
              },
              "scheduleId" : {
                "type" : "number",
                "description" : "새로 생성한 Schedule Id"
              }
            }
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "users-travel-types2059588752" : {
        "type" : "object",
        "properties" : {
          "answer" : {
            "type" : "array",
            "description" : "밸런스 게임 응답",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "id" : {
            "type" : "string",
            "description" : "사용자 ID"
          }
        }
      },
      "users-travel-types-450931574" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "data" : {
            "type" : "object",
            "properties" : {
              "typeDesc" : {
                "type" : "string",
                "description" : "여행 타입 설명"
              },
              "typeImageUrl" : {
                "type" : "string",
                "description" : "여행 타입 이미지"
              },
              "typeName" : {
                "type" : "string",
                "description" : "여행 타입 이름"
              }
            }
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "schedules-copy-paste-schedule-597913930" : {
        "type" : "object",
        "properties" : {
          "newScheduleId" : {
            "type" : "number",
            "description" : "plan들을 복사할 기존의 Schedule Id"
          },
          "planIdList" : {
            "type" : "array",
            "description" : "복사할 PlanId 리스트",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "userId" : {
            "type" : "string",
            "description" : "사용자 ID"
          },
          "scheduleId" : {
            "type" : "number",
            "description" : "PlanId가 위치한 Schedule Id"
          }
        }
      },
      "schedules-copy-create-schedule-290318001" : {
        "type" : "object",
        "properties" : {
          "planIdList" : {
            "type" : "array",
            "description" : "복사할 PlanId 리스트",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "userId" : {
            "type" : "string",
            "description" : "사용자 ID"
          },
          "scheduleId" : {
            "type" : "number",
            "description" : "PlanId가 위치한 Schedule Id"
          }
        }
      },
      "login-1664081379" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "login486549215" : {
        "type" : "object"
      }
    }
  }
}